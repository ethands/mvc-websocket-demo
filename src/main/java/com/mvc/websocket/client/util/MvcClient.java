package com.mvc.websocket.client.util;

import com.mvc.websocket.client.impl.CustomHandler;
import lombok.Data;

import java.net.URISyntaxException;

/**
 * @author qiyichen
 * @create 2018/9/4 16:13
 */
@Data
public class MvcClient {

    private static String loginUrl;
    private static String username;
    private static String pw;
    private static String wsUrl;
    private static MvcMsgHandler handler;

    public static void start() {
        System.out.println("连接中");
        try {
            MvcWebSocket webSocket = new MvcWebSocket(wsUrl, loginUrl, username, pw, handler);
            webSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void build(String wsUrl, String loginUrl, String username, String pw, CustomHandler handler) {
        MvcClient.wsUrl = wsUrl;
        MvcClient.loginUrl = loginUrl;
        MvcClient.username = username;
        MvcClient.pw = pw;
        MvcClient.handler = handler;
    }
}
