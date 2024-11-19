package com.example.biling_system.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TransactionResponse {
    long id;
    String transactionCode;
    long idBill;
    Date transactionDate;
    Double totalAmount;
    String paymentMethod;
}
