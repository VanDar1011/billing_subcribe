package com.example.biling_system.service;

import com.example.biling_system.Repository.BillRepository;
import com.example.biling_system.Repository.TransactionRepository;
import com.example.biling_system.dto.request.TransactionRequest;
import com.example.biling_system.dto.response.TransactionResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.TransactionMapper;
import com.example.biling_system.model.Bill;
import com.example.biling_system.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {
    private final BillRepository billRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public String genTransactionCode() {
        long currentMilliseconds = new Date().getTime();
        String strCode = currentMilliseconds + "";
        return strCode;
    }

    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        Bill bill =
                billRepository.findBillByBillCode(String.valueOf(transactionRequest.getBillId()));
        if(bill == null) {
            throw new AppException(ErrorCode.BILL_NOT_FOUND);
        }
        Transaction transaction = new Transaction();
        transaction.setIdBill(bill);
        transaction.setTotalAmount(bill.getTotalAmount());
        Date date = new Date();
        transaction.setTransactionDate(new java.sql.Date(date.getTime()) );
        transaction.setTransactionCode(genTransactionCode());
        transaction.setStatus("Oce");
        Transaction repositoryTransaction = transactionRepository.save(transaction);
        return transactionMapper.toResponse(repositoryTransaction);
    }
}
