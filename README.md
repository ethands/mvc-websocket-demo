# mvc-websocket-demo

## 项目说明
该项目为使用websocket连接wallet-shell并监听的简单demo
包含了心跳发送、自动回执、断线重连机制

## 如何使用
继承MvcMsgHandler并实现onMessage方法

```Java
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

```
传入初始化参数并启动
```Java
  String wsUrl = "ws://localhost:1988/websocket/eth";
  String loginUrl = "http://localhost:1988/admin";
  String username = "admin";
  String pw = "admin";
  CustomHandler handler = new CustomHandler();
  MvcClient.build(wsUrl, loginUrl, username, pw, handler);
  MvcClient.start();
```
