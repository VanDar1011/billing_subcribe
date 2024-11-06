package com.example.biling_system.dto.request;


import com.example.biling_system.model.Subcriber;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    String codeCus;

    String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dateOfBirth;

    String gender;

    String address;

    String email;

    String identifyCode;


}
