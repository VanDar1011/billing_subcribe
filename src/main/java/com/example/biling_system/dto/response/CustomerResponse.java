package com.example.biling_system.dto.response;

import com.example.biling_system.model.Subcriber;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    String codeCus;

    String name;

    LocalDate dateOfBirth;

    String gender;

    String address;

    String email;

    String identifyCode;

    List<String> subcribers;
}
