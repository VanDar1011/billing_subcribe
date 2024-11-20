package com.example.biling_system.exception;

public class TransactionNotCorrect extends RuntimeException {
    public TransactionNotCorrect(String message) {
        super(message);
    }
}
