package com.example.biling_system.dto.request;

import com.example.biling_system.model.Customer;
import com.example.biling_system.model.UsagePackage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class SubcriberRequest {
    String codeNumber;

    String phoneNumber;

    String phoneNumberType;

    LocalDate dayActive;

    LocalDate dayInactive;

    String seriPhoneNumber;

    boolean status;

    Customer idCustomer;


    List<UsagePackage> usagePackages;
}
