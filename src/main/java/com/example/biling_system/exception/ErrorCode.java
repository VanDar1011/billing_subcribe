package com.example.biling_system.exception;


public enum ErrorCode {

    CUSTOMER_NOT_FOUND(1001, "Customer Not Found"),
    CUSTOMER_EXIST(1011, "Customer Already Exist"),

    SUBCRIBER_NOT_FOUND(1002, "Subcriber Not Found"),
    PACKAGE_TYPE_NOT_FOUND(1003, "Package Type Not Found"),
    USAGE_PACKAGE_NOT_FOUND(1004, "Usage Package Not Found"),
    BILL_NOT_FOUND(1005, "Bill Not Found"),


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
