package com.example.biling_system.dto.response;


import com.example.biling_system.model.UsagePackage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageTypeResponse {
    String packageCode;

    String packageName;

    String packageCapacity;

    Double packagePrice;

}
