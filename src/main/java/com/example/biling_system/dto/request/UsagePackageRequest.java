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
public class UsagePackageRequest {

    @NotNull(message = "NOT_NULL")
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date startDay;

    @NotNull(message = "NOT_NULL")
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date endDay;

    @NotNull(message = "NOT_NULL")
    String checkoutStatus;

    @Size(max = 20, message = "NOTE_VALID")
    String note;

    @NotNull(message = "NOT_NULL")
    Long idPackageType;
}
