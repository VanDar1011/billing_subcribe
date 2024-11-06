package com.example.biling_system.exception;

public enum ErrorCode {

    CUSTOMER_NOT_FOUND(1001, "Customer Not Found"),
    SUBCRIBER_NOT_FOUND(1002, "Subcriber Not Found"),


    ;
    ErrorCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    private long code;
    private String message;

    public long getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
