package com.example.biling_system.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;


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
    @NotBlank(message = "NAME_NOT_VALID")
    String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "DATE_NOT_VALID")
    @NotNull(message = "NOT_BLANK")
    Date dateOfBirth;

    @Pattern(regexp = "^(Male|Female|Other)$", message = "GENDER_NOT_VALID")
    @NotBlank(message = "NOT_BLANK")
    @Size(max = 10)
    String gender;

    @Size(max = 100)
    @NotBlank(message = "NOT_BLANK")
    String address;

    @Size(max = 100)
    @Email(message = "EMAIL_NOT_VALID")
    @NotBlank(message = "NOT_BLANK")
    String email;

    @NotBlank(message = "IDENTIFY_NOT_VALID")
    @Pattern(regexp = "\\d{12}", message = "IDENTIFY_NOT_VALID")
    String identifyCode;


}
