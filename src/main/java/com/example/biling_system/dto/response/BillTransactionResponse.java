package com.example.biling_system.dto.response;

import jakarta.persistence.Column;

import java.sql.Date;

public class BillTransactionResponse {
    String transactionCode;
    String codeCus;
    String nameCustomer;
    String identifyCode;
    String gender_customer;
    String address;
    String codeNumber;
    String phoneNumber;
    String phoneNumberType;
    String packageCode;
    String packageName;
    String packageCapacity;
    Date startDay;
    Date endDay;
    Double totalAmount;
    Date transactionDate;
}
