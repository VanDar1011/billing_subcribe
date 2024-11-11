package com.example.biling_system.dto.request;


import jakarta.validation.constraints.NotBlank;
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


    @NotBlank(message = "NOT_BLANK")
    @Size(max = 10, message = "PACKAGE_NOT_VALID")
    String packageCode;

    @NotBlank(message = "NOT_BLANK")
    String packageName;

    @NotBlank(message = "NOT_BLANK")
    String packageCapacity;

    @NotBlank(message = "NOT_BLANK")
    Double packagePrice;


}
