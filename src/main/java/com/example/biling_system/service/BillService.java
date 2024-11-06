package com.example.biling_system.service;

import com.example.biling_system.Repository.BillRepository;
import com.example.biling_system.dto.request.BillRequest;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.BillMapper;
import com.example.biling_system.model.Bill;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillService {
    BillRepository billRepository;
    BillMapper billMapper;

    public Bill createBill(BillRequest request) {
        var bill = billMapper.toBill(request);
        return billRepository.save(bill);
    }

    public Page<Bill> findAllBills(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return billRepository.findAll(pageable);
    }

    public Bill findBillById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
    }

    public Bill updateBill(Long id, BillRequest request) {
        var bill = findBillById(id);
        bill = billMapper.toBill(request);
        return billRepository.save(bill);
    }

    public Bill deleteBill(Long id) {
        var bill = findBillById(id);
        billRepository.delete(bill);
        return bill;
    }

}
