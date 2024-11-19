package com.example.biling_system.controller;


import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.BillTransactionsResponse;
import com.example.biling_system.service.BillTransactionsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/billtransactions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillTransactionsController {
    BillTransactionsService billTransactionsService;

    @GetMapping("/find")
    public ApiResponse<List<BillTransactionsResponse>> findBillTransactions(
            @RequestParam(required = false) String packageName,
            @RequestParam(required = false) String packageCode,
            @RequestParam(required = false) Date transactionDate) {

        ApiResponse<List<BillTransactionsResponse>> response = new ApiResponse<>();

        List<BillTransactionsResponse> transactions = billTransactionsService.billTransactionResponseList(
                packageName, packageCode, transactionDate);

        response.setData(transactions);

        return response;
    }


    @GetMapping
    public ApiResponse<String> createBillTransaction(@RequestBody
                                                     List<BillTransactionsResponse> billTransactionsResponse) {
        ApiResponse<String> response = new ApiResponse<>();
        billTransactionsService.createfileBillTransactions(billTransactionsResponse);
        return response;
    }
}
