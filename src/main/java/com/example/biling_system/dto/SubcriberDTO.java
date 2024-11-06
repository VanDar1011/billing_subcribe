package com.example.biling_system.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubcriberDTO {

    String codeNumber;
    String phoneNumber;
    String phoneNumberType;
    LocalDate dayActive;
    LocalDate dayInactive;
    String seriPhoneNumber;
    boolean status;
}
