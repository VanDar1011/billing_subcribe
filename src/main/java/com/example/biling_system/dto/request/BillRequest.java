package com.example.biling_system.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class BillRequest {

    @NotBlank(message = "NOT_BLANK")
    @Size(max = 10, message = "BILL_CODE_NOT_VALID")
    String billCode;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "BILL_NULL")
    Date establishtmentDay;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "NOT_BLANK")
    Date collectionDay;

    @NotNull(message = "NOT_BLANK")
    Double totalAmount;

    String status = "PENDING";

    @NotNull(message = "NOT_BLANK")
    Long idUsagePackage;

    @NotNull(message = "NOT_BLANK")
    int billingMonth;

    // hoa don cua ki nao

}
