package com.mvc.websocket.client.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rx.Scheduler;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * mvc-transaction-listener-websocket
 *
 * @author mvc
 * @create 2018/9/3 15:02
 */
@Data
public class MvcWebSocket extends WebSocketClient {

    private MvcMsgHandler handler;
    private RestTemplate restTemplate = new RestTemplate();
    private String host;
    private String loginUrl;
    private String username;
    private String pw;
    private String token;
    /**
     * MILLISECONDS
     */
    private Integer initialDelay = 1000;

    private Stack<Transaction> stack = new Stack<Transaction>();
    private final Scheduler scheduler = Schedulers.newThread();

    public MvcWebSocket(String host, String loginUrl, String username, String pw, MvcMsgHandler handler) throws URISyntaxException {
        super(new URI(host), new Draft_17());
        this.loginUrl = loginUrl;
        this.username = username;
        this.pw = pw;
        this.host = host;
        this.handler = handler;
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        scheduler.createWorker().schedule(new Action0() {
            @Override
            public void call() {
                while (true) {
                    try {
                        if (!stack.empty()) {
                            Transaction transaction = stack.pop();
                            Boolean result = handler.onMessage(transaction);
                            if (null != result && result) {
                                WsMessage wsMessage = new WsMessage();
                                wsMessage.setType("message");
                                wsMessage.setContent(getContent(transaction.getId(), transaction.getDepth(), transaction.getOprType()));
                                send(JSON.toJSONString(wsMessage));
                            }
                        }
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }
        }, initialDelay, TimeUnit.MILLISECONDS);
        WsMessage message = new WsMessage();
        message.setType("token");
        message.setContent(getToken());
        send(JSON.toJSONString(message));
        heartMsg();
    }

    @Override
    public void onMessage(String text) {
        final Transaction transaction = JSON.parseObject(text, Transaction.class);
        stack.push(transaction);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("close");
        MvcClient.start();
    }

    @Override
    public void onError(Exception e) {
        System.out.println("onError");
    }

    private void heartMsg() {
        Schedulers.newThread().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                while (true) {
                    System.out.println("心跳发送中");
                    send("heart");
                    try {
                        Thread.sleep(30 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private String getToken() {
        JSONObject param = null;
        try {
            param = new JSONObject();
            param.put("username", username);
            param.put("password", pw);
            ResponseEntity<Result> result = restTemplate.postForEntity(loginUrl, param, Result.class);
            token = result.getBody().getData().getToken();
            return token;
        } catch (RestClientException e) {
            System.out.println("登录失败");
            return null;
        }
    }

    private String getContent(BigInteger id, Integer depth, String oprType) {
        Map<String, Object> content = new HashMap(4);
        content.put("id", id);
        content.put("depth", depth);
        content.put("oprType", oprType);
        return JSON.toJSONString(content);
    }
}
