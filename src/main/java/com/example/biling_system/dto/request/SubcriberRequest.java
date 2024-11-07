package com.example.biling_system.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotNull(message = "SUBCRIBER_NULL")
    String codeNumber;

    @Pattern(regexp = "\\d{12}", message = "PHONE_NUMBER_NOT_VALID")
    @NotNull(message = "SUBCRIBER_NULL")
    String phoneNumber;

    @NotNull(message = "SUBCRIBER_NULL")
    String phoneNumberType;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "SUBCRIBER_NULL")
    LocalDate dayActive;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "SUBCRIBER_NULL")
    LocalDate dayInactive;

    @NotNull(message = "SUBCRIBER_NULL")
    String seriPhoneNumber;

    @NotNull(message = "SUBCRIBER_NULL")
    boolean status;

    @NotNull(message = "SUBCRIBER_NULL")
    Long idCustomer;
}
