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
    String codeCus;
    @NotNull(message = "NAME_NOT_VALID")
    String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "DATE_NOT_VALID")
    LocalDate dateOfBirth;

    @Pattern(regexp = "^(Male|Female|Other)$", message = "GENDER_NOT_VALID")
    String gender;

    String address;

    @Email(message = "EMAIL_NOT_VALID")
    String email;

    @Pattern(regexp = "\\d{12}", message = "IDENTY_NOT_VALID")
    String identifyCode;


}
