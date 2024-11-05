package com.example.biling_system.contant;

public enum ResponseCode {
    OK("ok"), FAIL("fail");
    private final String status;

    ResponseCode(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    }
