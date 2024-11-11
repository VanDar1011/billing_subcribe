package com.example.biling_system.controller;


import com.example.biling_system.Repository.PackageTypeRepository;
import com.example.biling_system.dto.request.PackageTypeRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.PackageTypeResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.service.PackageTypeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/packagetypes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PackageTypeController {
    PackageTypeService packageTypeService;
    PackageTypeRepository packageTypeRepository;

    @PostMapping
    public ApiResponse<PackageTypeResponse> create(@RequestBody @Valid PackageTypeRequest request) {
        if (packageTypeRepository.existsByPackageCode(request.getPackageCode())) {
            throw new AppException(ErrorCode.PACKAGE_EXIST);
        } else {
            request.setPackageCode(request.getPackageCode().toUpperCase());
        }
        ApiResponse<PackageTypeResponse> response = new ApiResponse<>();
        response.setData(packageTypeService.createPackageType(request));
        return response;
    }

    @GetMapping
    public ApiResponse<List<PackageTypeResponse>> getAll(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<PackageTypeResponse>> response = new ApiResponse<>();
        response.setData(packageTypeService.findAllPackageTypes(page, size).getContent());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<PackageTypeResponse> getById(@PathVariable("id") long id) {
        ApiResponse<PackageTypeResponse> response = new ApiResponse<>();
        response.setData(packageTypeService.findPackageTypeById(id));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<PackageTypeResponse> update(@PathVariable("id") long id,
                                                   @RequestBody @Valid PackageTypeRequest request) {
        ApiResponse<PackageTypeResponse> response = new ApiResponse<>();
        var packageType = packageTypeService.updatePackageTypeById(id, request);
        response.setData(packageType);
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<PackageTypeResponse> deleteCustomer(@PathVariable("id") long id) {

        ApiResponse<PackageTypeResponse> apiResponse = new ApiResponse<>();
        var customer = packageTypeService.deletePackageTypeById(id);
        if (customer != null) {
            apiResponse.setData(customer);
        }
        return apiResponse;
    }
}
