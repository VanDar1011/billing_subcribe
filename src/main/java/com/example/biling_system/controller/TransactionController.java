package com.example.biling_system.controller;

import com.example.biling_system.dto.request.TransactionRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.TransactionResponse;
import com.example.biling_system.model.Bill;
import com.example.biling_system.model.Transaction;
import com.example.biling_system.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transactions")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<ApiResponse<String>> getTransactions() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData(transactionService.genTransactionCode());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<TransactionResponse>>
    createTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        ApiResponse<TransactionResponse> apiResponse = new ApiResponse<>();
        TransactionResponse transaction = transactionService.createTransaction(transactionRequest);
        apiResponse.setData(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);


    }
}
