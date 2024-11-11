package com.example.biling_system.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {
    long id;

    String billCode;

    LocalDate establishtmentDay;

    LocalDate collectionDay;

    Double totalAmount;

    String status;

    Long idUsagePackage;

}
