package com.example.biling_system.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "CODE_CUS_NOT_VALID")
    @Size(max = 20, message = "CODE_CUS_NOT_VALID")
    String codeCus;
    @Size(max = 50, message = "NAME_NOT_VALID")
    @NotNull(message = "NAME_NOT_VALID")
    String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "DATE_NOT_VALID")
    @NotNull(message = "CUSTOMER_NULL")
    LocalDate dateOfBirth;

    @Pattern(regexp = "^(Male|Female|Other)$", message = "GENDER_NOT_VALID")
    @NotNull(message = "CUSTOMER_NULL")
    @Size(max = 10)
    String gender;

    @Size(max = 100)
    @NotNull(message = "CUSTOMER_NULL")
    String address;

    @Size(max =100)
    @Email(message = "EMAIL_NOT_VALID")
    String email;

    @NotBlank(message = "IDENTIFY_NOT_VALID")
    @Pattern(regexp = "\\d{12}", message = "IDENTY_NOT_VALID")
    String identifyCode;


}
