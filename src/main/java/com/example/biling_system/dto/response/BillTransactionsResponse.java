package com.example.biling_system.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BillTransactionsResponse {
    long id;
    String transactionCode;
    String codeCus;
    String nameCustomer;
    String identifyCode;
    String genderCustomer;
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
