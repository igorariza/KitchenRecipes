package com.kitchen.recipeskotlin.data.model;


public class ModelResponse {
    private String protocol;
    private int code;
    private String message;
    private String url;

    public ModelResponse(String protocol, int code, String message, String url) {
        this.protocol = protocol;
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public ModelResponse() {
    }

    public String getProtocol() {
        return protocol;
    }
    
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

}