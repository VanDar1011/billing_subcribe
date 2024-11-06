package com.example.biling_system.controller;


import com.example.biling_system.dto.request.PackageTypeRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.model.PackageType;
import com.example.biling_system.service.PackageTypeService;
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

    @PostMapping
    public ApiResponse<PackageType> create(@RequestBody PackageTypeRequest request) {
        ApiResponse<PackageType> response = new ApiResponse<>();
        response.setData(packageTypeService.createPackageType(request));
        return response;
    }

    @GetMapping
    public ApiResponse<List<PackageType>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<PackageType>> response = new ApiResponse<>();
        response.setData(packageTypeService.findAllPackageTypes(page,size).getContent());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<PackageType> getById(@PathVariable("id") long id) {
        ApiResponse<PackageType> response = new ApiResponse<>();
        response.setData(packageTypeService.findPackageTypeById(id));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<PackageType> update(@PathVariable("id") long id, @RequestBody PackageTypeRequest request) {
        ApiResponse<PackageType> response = new ApiResponse<>();
        PackageType packageType = packageTypeService.updatePackageTypeById(id,request);
        response.setData(packageType);
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<PackageType> delete(@PathVariable("id") long id) {
        ApiResponse<PackageType> response = new ApiResponse<>();
        response.setData(packageTypeService.deletePackageTypeById(id));
        return response;
    }
}
