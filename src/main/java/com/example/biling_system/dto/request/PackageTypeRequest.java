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


    @NotNull(message = "NOT_NULL")
    @Size(max = 10, message = "PACKAGE_NOT_VALID")
    String packageCode;

    @NotNull(message = "NOT_NULL")
    String packageName;

    @NotNull(message = "NOT_NULL")
    String packageCapacity;

    @NotNull(message = "NOT_NULL")
    Double packagePrice;


}
