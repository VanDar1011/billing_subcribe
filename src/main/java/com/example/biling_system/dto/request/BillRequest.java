package com.example.biling_system.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {

    @NotNull(message = "NOT_NULL")
    @Size(max = 10, message = "BILL_CODE_NOT_VALID")
    String billCode;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "NOT_NULL")
    Date establishtmentDay;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "NOT_NULL")
    Date collectionDay;

    @NotNull(message = "NOT_NULL")
    Double totalAmount;

    @NotNull(message = "NOT_NULL")
    String status;

    @NotNull(message = "NOT_NULL")
    Long idUsagePackage;
    int billingMonth;

    // hoa don cua ki nao

}
