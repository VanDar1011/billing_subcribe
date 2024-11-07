package com.example.biling_system.exception;


public enum ErrorCode {

    CUSTOMER_NOT_FOUND(1010, "Customer Not Found"),
    CUSTOMER_EXIST(1011, "Customer Already Exist"),
    CUSTOMER_NULL(1012, "Must be filled out"),
    CODE_CUS_NOT_VALID(1013, "CodeCustomer must be filled out without spaces and at least 20 characters"),
    NAME_NOT_VALID(1014, "Name must be filled and at least 50 characters"),
    DATE_NOT_VALID(1015, "Date must be in the past"),
    GENDER_NOT_VALID(1016, "please choose gender : male or female or other"),
    EMAIL_NOT_VALID(1017, "Email must be @gmail.com"),
    IDENTIFY_NOT_VALID(1018, "Identify Code must at least 12 character and not spaces"),


    SUBCRIBER_NOT_FOUND(1020, "Subcriber Not Found"),
    PHONE_EXIST(1021, "Phone Number or Code Number Already Exist"),
    SUBCRIBER_NULL(1022, "Must be filled out"),
    PHONE_NUMBER_NOT_VALID(1023, "Phone Number must be at least 10 character"),

    PACKAGE_TYPE_NOT_FOUND(1003, "Package Type Not Found"),
    PACKAGE_EXIST(1004, "PackageType Already Exist"),
    PACKAGE_NULL(1005, "Must be filled out"),
    PACKAGE_NOT_VALID(1006, "PackageType must be at least 10 character"),

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
