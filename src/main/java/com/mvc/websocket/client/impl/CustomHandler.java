package com.mvc.websocket.client.impl;

import com.alibaba.fastjson.JSON;
import com.mvc.websocket.client.util.MvcMsgHandler;
import com.mvc.websocket.client.util.Transaction;

/**
 * 用户需自己继承MvcMsgHandler并添加持久化方法,如果操作成功则返回true
 * @author mvc
 * @create 2018/9/4 17:06
 */
public class CustomHandler implements MvcMsgHandler {
    @Override
    public Boolean onMessage(Transaction transaction) {
        // 业务处理逻辑
        if (true) {
            System.out.println("收到消息" + JSON.toJSONString(transaction));
            //持久化成功
            return true;
        }
        return false;
    }
}
