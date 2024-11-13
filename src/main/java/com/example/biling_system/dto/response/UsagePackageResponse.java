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
public class UsagePackageResponse {
    long id;
    Date startDay;

    Date endDay;

    String checkoutStatus;

    String note;

    Long idSubcriber;

    PackageTypeResponse idPackageType;

}
