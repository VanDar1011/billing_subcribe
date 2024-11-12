package com.example.biling_system.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;



@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class SubcriberRequest {

    @NotBlank(message = "NOT_BLANK")
    @Size(max = 10, message = "CODE_NUMBER_VALID")
    String codeNumber;

    @Pattern(regexp = "\\d{10}", message = "PHONE_NUMBER_NOT_VALID")
    @NotBlank(message = "NOT_BLANK")
    String phoneNumber;

    @Size(max = 20)
    @NotBlank(message = "NOT_BLANK")
    String phoneNumberType;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "NOT_BLANK")
    Date dayActive;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "NOT_BLANK")
    Date dayInactive;

    @Size(max = 20, message = "SERI_PHONE_NUMBER_VALID")
    @NotBlank(message = "NOT_BLANK")
    String seriPhoneNumber;

    @NotNull(message = "NOT_BLANK")
    boolean status;

    @NotNull(message = "NOT_BLANK")
    Long idCustomer;
}
