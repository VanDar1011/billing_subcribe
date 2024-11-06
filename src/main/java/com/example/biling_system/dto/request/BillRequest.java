package com.example.biling_system.dto.request;

import com.example.biling_system.model.Customer;
import com.example.biling_system.model.UsagePackage;
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
public class BillRequest {
    String billCode;

    Customer idCustomer;

    String subcibeCode;


    Date establishtmentDay;

    Date collectionDay;

    Double totalAmount;

    String status;

    UsagePackage idUsagePackage;

}
