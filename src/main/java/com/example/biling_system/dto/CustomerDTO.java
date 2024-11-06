package com.example.biling_system.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {
    String codeCus;
    String name;
    LocalDate dateOfBirth;
    String gender;
    String address;
    String email;
    String identifyCode;
}
