package com.example.biling_system.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private HttpStatus httpStatus;

    public AppException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
