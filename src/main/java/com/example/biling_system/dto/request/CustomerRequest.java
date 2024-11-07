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

    @NotBlank
    String codeCus;
    @NotNull
    String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past
    LocalDate dateOfBirth;

    @Pattern(regexp = "^(Male|Female|Other)$")
    String gender;

    String address;

    @Email
    String email;

    @Size(max = 12)
    @NotBlank
    String identifyCode;


}
