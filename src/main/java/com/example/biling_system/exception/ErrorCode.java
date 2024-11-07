package com.example.biling_system.exception;


public enum ErrorCode {

    CUSTOMER_NOT_FOUND(1001, "Customer Not Found"),
    CUSTOMER_EXIST(1011, "Customer Already Exist"),
    CODE_CUS_NOT_VALID(1012, "CodeCustomer must be filled out without spaces"),
    NAME_NOT_VALID(1013, "Name must be filled"),
    DATE_NOT_VALID(1014, "Date must be in the past"),
    GENDER_NOT_VALID(1015, "please choose gender : male or female or other"),
    EMAIL_NOT_VALID(1016, "Email must be @gmail.com"),
    IDENTY_NOT_VALID(1017, "identifycode must at least 12 character"),


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
