package com.example.biling_system.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TransactionResponse {
    String transactionCode;
    Date transactionDate;
    Double totalAmount;
    String status;
}
