package com.example.biling_system.controller;

import com.example.biling_system.dto.request.BillRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.model.Bill;
import com.example.biling_system.service.BillService;
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

    @PostMapping
    public ApiResponse<Bill> create(@RequestBody BillRequest request) {
        ApiResponse<Bill> response = new ApiResponse<>();
        var bill = billService.createBill(request);
        response.setData(bill);
        return response;
    }

    @GetMapping
    public ApiResponse<List<Bill>> getAllBills(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<Bill>> response = new ApiResponse<>();
        response.setData(billService.findAllBills(page, size).getContent());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<Bill> getBillById(@PathVariable("id") long id) {
        ApiResponse<Bill> response = new ApiResponse<>();
        response.setData(billService.findBillById(id));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<Bill> updateBill(@PathVariable("id") long id, @RequestBody BillRequest request) {
        ApiResponse<Bill> response = new ApiResponse<>();
        response.setData(billService.updateBill(id, request));
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Bill> deleteBill(@PathVariable("id") long id) {
        ApiResponse<Bill> response = new ApiResponse<>();
        response.setData(billService.deleteBill(id));
        return response;
    }



}
