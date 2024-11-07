package com.example.biling_system.dto.request;

import com.example.biling_system.model.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class SubcriberRequest {
    String codeNumber;

    String phoneNumber;

    String phoneNumberType;

    LocalDate dayActive;

    LocalDate dayInactive;

    String seriPhoneNumber;

    boolean status;

    Long idCustomer;

}
