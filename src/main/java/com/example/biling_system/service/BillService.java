package com.example.biling_system.service;

import com.example.biling_system.Repository.BillRepository;
import com.example.biling_system.dto.request.BillRequest;
import com.example.biling_system.dto.response.BillResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.BillMapper;
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

    public BillResponse createBill(BillRequest request) {
        var bill = billMapper.toBill(request);
        billRepository.save(bill);
        return billMapper.toBillResponse(bill);
    }

    public Page<BillResponse> findAllBills(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        billRepository.findAll(pageable);
        return billMapper.toBillResponsePage(billRepository.findAll(pageable));
    }

    public BillResponse findBillById(Long id) {
        var bill = billRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        return billMapper.toBillResponse(bill);
    }

    public BillResponse updateBill(Long id, BillRequest request) {
        var bill = billRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        bill = billMapper.toBill(request);
        billRepository.save(bill);
        return billMapper.toBillResponse(bill);
    }

    public BillResponse deleteBill(Long id) {
        var bill = billRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        billRepository.delete(bill);
        return billMapper.toBillResponse(bill);
    }

}
