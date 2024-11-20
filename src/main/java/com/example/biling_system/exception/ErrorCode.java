package com.example.biling_system.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    CUSTOMER_NOT_FOUND(1010, "Customer Not Found", HttpStatus.NOT_FOUND),
    CUSTOMER_EXIST(1011, "Customer Already Exist", HttpStatus.BAD_REQUEST),
    CODE_CUS_NOT_VALID(1013, "CodeCustomer must be filled out without spaces and at least 20 characters", HttpStatus.BAD_REQUEST),
    NAME_NOT_VALID(1014, "Name must be filled and at least 50 characters", HttpStatus.BAD_REQUEST),
    DATE_NOT_VALID(1015, "Date must be in the past", HttpStatus.BAD_REQUEST),
    GENDER_NOT_VALID(1016, "Please choose gender: Male or Female or Other", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_VALID(1017, "Email must be @gmail.com", HttpStatus.BAD_REQUEST),
    IDENTIFY_NOT_VALID(1018, "Identify Code must at least 12 characters and not spaces", HttpStatus.BAD_REQUEST),
    IDENTIFY_EXISTED(1019, "Identify Code Already Exist", HttpStatus.BAD_REQUEST),

    SUBSCRIBER_NOT_FOUND(1020, "Subscriber Not Found", HttpStatus.NOT_FOUND),
    PHONE_EXIST(1021, "Phone Number or Code Number Already Exist", HttpStatus.BAD_REQUEST),
    CODE_NUMBER_VALID(1023, "Code Number must be at least 10 characters", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_NOT_VALID(1024, "Phone Number must be at least 10 characters", HttpStatus.BAD_REQUEST),
    SERI_PHONE_NUMBER_VALID(1025, "Serial Number must be at least 20 characters", HttpStatus.BAD_REQUEST),

    PACKAGE_TYPE_NOT_FOUND(1030, "Package Type Not Found", HttpStatus.NOT_FOUND),
    PACKAGE_EXIST(1031, "Package Type Already Exist", HttpStatus.BAD_REQUEST),
    PACKAGE_NOT_VALID(1033, "Package Type must be at least 10 characters", HttpStatus.BAD_REQUEST),

    USAGE_PACKAGE_NOT_FOUND(1040, "Usage Package Not Found", HttpStatus.NOT_FOUND),
    NOTE_VALID(1042, "Note is too long", HttpStatus.BAD_REQUEST),

    BILL_NOT_FOUND(1050, "Bill Not Found", HttpStatus.NOT_FOUND),
    BILL_EXIST(1051, "Bill Already Exist", HttpStatus.BAD_REQUEST),
    BILL_CODE_NOT_VALID(1053, "Code Number must be at least 10 characters", HttpStatus.BAD_REQUEST),

    NOT_BLANK(9999, "Must be filled out", HttpStatus.BAD_REQUEST),
    TRANSACTION_NOT_CORRECT(3000, "Transaction Not Correct", HttpStatus.BAD_REQUEST),
    NOT_FOUND_TRANSACTION(3001, "Transaction Not Found", HttpStatus.NOT_FOUND);

    private final long code;
    private final String message;
    private final HttpStatus httpStatus;


    ErrorCode(long code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
