package com.example.biling_system.controller;

import com.example.biling_system.Repository.BillRepository;
import com.example.biling_system.dto.request.BillRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.BillResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.service.BillService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bills")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {
    BillService billService;
    BillRepository billRepository;
//    @PostMapping
//    public ApiResponse<BillResponse> create(@RequestBody @Valid BillRequest request) {
//        if (billRepository.existsByBillCode(request.getBillCode())){
//            throw new AppException(ErrorCode.BILL_EXIST);
//        }
//        ApiResponse<BillResponse> response = new ApiResponse<>();
//        var bill = billService.createBill(request);
//        response.setData(bill);
//        return response;
//    }

    @GetMapping
    public ApiResponse<List<BillResponse>> getAllBills(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<BillResponse>> response = new ApiResponse<>();
        response.setData(billService.findAllBills(page, size).getContent());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<BillResponse> getBillById(@PathVariable("id") long id) {
        ApiResponse<BillResponse> response = new ApiResponse<>();
        response.setData(billService.findBillById(id));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<BillResponse> updateBill(@PathVariable("id") long id, @RequestBody @Valid BillRequest request) {
        ApiResponse<BillResponse> response = new ApiResponse<>();
        response.setData(billService.updateBill(id, request));
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<BillResponse> deleteBill(@PathVariable("id") long id) {
        ApiResponse<BillResponse> response = new ApiResponse<>();
        response.setData(billService.deleteBill(id));
        return response;
    }
    // tim kiem
    // ds hoa don cua thue bao, hoac cua customer



}
