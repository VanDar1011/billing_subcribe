package com.example.biling_system.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    long id;

    String codeCus;

    String name;

    Date dateOfBirth;

    String gender;

    String address;

    String email;

    String identifyCode;

    List<String> subcribers;

}
