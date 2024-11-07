package com.example.biling_system.dto.request;


import com.example.biling_system.model.PackageType;
import com.example.biling_system.model.Subcriber;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class UsagePackageRequest {
    Date startDay;

    Date endDay;

    String checkoutStatus;

    String note;

    Long IDsubcriber;

    Long idPackageType;
}
