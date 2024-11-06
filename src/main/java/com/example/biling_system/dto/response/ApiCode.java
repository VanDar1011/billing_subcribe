package com.example.biling_system.dto.response;

public enum ApiCode {


    ;
    private long code;
    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ApiCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
