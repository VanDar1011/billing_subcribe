package com.example.biling_system.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubcriberResponse {
    long id;

    String codeNumber;

    String phoneNumber;

    String phoneNumberType;

    LocalDate dayActive;

    LocalDate dayInactive;

    String seriPhoneNumber;

    boolean status;

    Long idCustomer;

    List<UsagePackageResponse> usagePackages;

}
