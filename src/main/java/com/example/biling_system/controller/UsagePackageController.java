package com.example.biling_system.controller;


import com.example.biling_system.Repository.UsagePackageRepository;
import com.example.biling_system.dto.request.UsagePackageRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.UsagePackageResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.model.UsagePackage;
import com.example.biling_system.service.UsagePackageService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/usagepackages")
public class
UsagePackageController {
    UsagePackageService usagePackageService;
    UsagePackageRepository usagePackageRepository;

    @PostMapping
    public ApiResponse<UsagePackageResponse> create(@RequestBody @Valid UsagePackageRequest request) {
        ApiResponse<UsagePackageResponse> response = new ApiResponse<>();
        response.setData(usagePackageService.createUsagePackage(request));
        return response;
    }

    @GetMapping
    public ApiResponse<List<UsagePackageResponse>> findAll(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<UsagePackageResponse>> response = new ApiResponse<>();
        response.setData(usagePackageService.findAllUsagePackages(page, size).getContent());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<UsagePackageResponse> findById(@PathVariable("id") long id) {
        ApiResponse<UsagePackageResponse> response = new ApiResponse<>();
        response.setData(usagePackageService.findUsagePackageById(id));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<UsagePackageResponse> update(@PathVariable("id") long id,
                                                    @RequestBody @Valid UsagePackageRequest request) {
        ApiResponse<UsagePackageResponse> response = new ApiResponse<>();
        response.setData(usagePackageService.updateUsagePackage(id, request));
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<UsagePackageResponse> delete(@PathVariable("id") long id) {
        ApiResponse<UsagePackageResponse> response = new ApiResponse<>();
        response.setData(usagePackageService.deleteUsagePackage(id));
        return response;
    }

    @GetMapping("/updatelist")
    public ApiResponse<List<UsagePackageResponse>> updateStatus() {
        ApiResponse<List<UsagePackageResponse>> response = new ApiResponse<>();
        response.setData(usagePackageService.updateStatusUsagePackage());
        return response;
    }
}
