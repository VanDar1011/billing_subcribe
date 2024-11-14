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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionService {
    BillRepository billRepository;
    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;
    BillService billService;
    UsagePackageService usagePackageService;

    public String genTransactionCode() {
        long currentMilliseconds = new Date().getTime();
        String strCode = currentMilliseconds + "";
        return strCode;
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        Bill bill =
                billRepository.findById(transactionRequest.getIdBill())
                        .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        Transaction transaction = new Transaction();
        transaction.setIdBill(bill);
        transaction.setTotalAmount(bill.getTotalAmount());
        Date date = new Date();
        transaction.setTransactionDate(new java.sql.Date(date.getTime()));
        transaction.setTransactionCode(genTransactionCode());
        transaction.setPaymentMethod(transactionRequest.getPaymentMethod());
        Transaction repositoryTransaction = transactionRepository.save(transaction);

        billService.updateBillStatus(bill.getId());
        usagePackageService.updateUsageStatus(bill.getIdUsagePackage());

        return transactionMapper.toResponse(repositoryTransaction);
    }
}
