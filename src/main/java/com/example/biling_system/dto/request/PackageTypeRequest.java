package com.example.biling_system.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageTypeRequest {


    @NotNull(message = "PACKAGE_NULL")
    @Size(max = 10, message = "PACKAGE_NOT_VALID")
    String packageCode;

    @NotNull(message = "PACKAGE_NULL")
    String packageName;

    @NotNull(message = "PACKAGE_NULL")
    String packageCapacity;

    @NotNull(message = "PACKAGE_NULL")
    Double packagePrice;


}
