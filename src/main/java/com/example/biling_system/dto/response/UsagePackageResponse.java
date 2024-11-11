package com.example.biling_system.dto.response;


import com.example.biling_system.model.PackageType;
import com.example.biling_system.model.Subcriber;
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
public class UsagePackageResponse {
    long id;

    LocalDate startDay;

    LocalDate endDay;

    String checkoutStatus;

    String note;

    Long idPackageType;

}
