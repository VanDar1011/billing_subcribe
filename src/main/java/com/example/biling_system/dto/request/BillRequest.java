package com.example.biling_system.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {

    @NotBlank(message = "NOT_BLANK")
    @Size(max = 10, message = "BILL_CODE_NOT_VALID")
    String billCode;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "NOT_BLANK")
    LocalDate establishtmentDay;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "NOT_BLANK")
    LocalDate collectionDay;

    @NotBlank(message = "NOT_BLANK")
    Double totalAmount;

    @NotBlank(message = "NOT_BLANK")
    String status;

    @NotBlank(message = "NOT_BLANK")
    Long idUsagePackage;

    @NotBlank(message = "NOT_BLANK")
    int billingMonth;

    // hoa don cua ki nao

}
