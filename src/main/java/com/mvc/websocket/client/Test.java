package com.mvc.websocket.client;

import com.mvc.websocket.client.impl.CustomHandler;
import com.mvc.websocket.client.util.MvcClient;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * test
 *
 * @author mvc
 * @create 2018/9/3 17:07
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException, URISyntaxException {
        String wsUrl = "ws://localhost:1988/websocket/eth";
        String loginUrl = "http://localhost:1988/admin";
        String username = "admin";
        String pw = "admin";
        CustomHandler handler = new CustomHandler();
        MvcClient.build(wsUrl, loginUrl, username, pw, handler);
        MvcClient.start();
    }
}
