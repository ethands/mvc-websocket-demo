package com.mvc.websocket.client.util;

public class Result {
    private int code;
    private String message;
    private TokenVO data;

    public Result() {
    }

    public int getCode() {
        return this.code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public TokenVO getData() {
        return this.data;
    }

    public Result setData(TokenVO data) {
        this.data = data;
        return this;
    }
}
