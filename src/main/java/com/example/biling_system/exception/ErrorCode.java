package com.example.biling_system.exception;


public enum ErrorCode {

    CUSTOMER_NOT_FOUND(1010, "Customer Not Found"),
    CUSTOMER_EXIST(1011, "Customer Already Exist"),
    CODE_CUS_NOT_VALID(1013, "CodeCustomer must be filled out without spaces and at least 20 characters"),
    NAME_NOT_VALID(1014, "Name must be filled and at least 50 characters"),
    DATE_NOT_VALID(1015, "Date must be in the past"),
    GENDER_NOT_VALID(1016, "please choose gender : male or female or other"),
    EMAIL_NOT_VALID(1017, "Email must be @gmail.com"),
    IDENTIFY_NOT_VALID(1018, "Identify Code must at least 12 character and not spaces"),


    SUBCRIBER_NOT_FOUND(1020, "Subcriber Not Found"),
    PHONE_EXIST(1021, "Phone Number or Code Number Already Exist"),
    CODE_NUMBER_VALID(1023, "Code Number must be at least 10 characters"),
    PHONE_NUMBER_NOT_VALID(1024, "Phone Number must be at least 10 character"),
    SERI_PHONE_NUMBER_VALID(1025, "Serial Number must be at least 20 characters"),


    PACKAGE_TYPE_NOT_FOUND(1030, "Package Type Not Found"),
    PACKAGE_EXIST(1031, "PackageType Already Exist"),
    PACKAGE_NOT_VALID(1033, "PackageType must be at least 10 character"),


    USAGE_PACKAGE_NOT_FOUND(1040, "Usage Package Not Found"),
    NOTE_VALID(1042, "Note is too long"),


    BILL_NOT_FOUND(1050, "Bill Not Found"),
    BILL_EXIST(1051, "Bill Already Exist"),
    BILL_CODE_NOT_VALID(1053, "Code Number must be at least 10 character"),


    NOT_NULL(9999, "Must be filled out"),


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
