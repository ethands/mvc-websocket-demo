package com.mvc.websocket.client.util;

import lombok.Data;

/**
 * WsMessage
 *
 * @author qiyichen
 * @create 2018/6/1 19:49
 */
@Data
public class WsMessage {
    private String type;
    private String content;

}
