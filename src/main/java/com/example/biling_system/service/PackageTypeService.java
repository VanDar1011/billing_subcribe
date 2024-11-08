package com.example.biling_system.service;


import com.example.biling_system.Repository.PackageTypeRepository;
import com.example.biling_system.dto.request.PackageTypeRequest;
import com.example.biling_system.dto.response.PackageTypeResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.PackageTypeMapper;
import com.example.biling_system.model.PackageType;
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
public class PackageTypeService {

    PackageTypeRepository packageTypeRepository;
    PackageTypeMapper packageTypeMapper;

    public PackageTypeResponse createPackageType(PackageTypeRequest request) {
        PackageType packageType = packageTypeMapper.toPackageType(request);
        packageTypeRepository.save(packageType);
        return packageTypeMapper.toPackageTypeResponse(packageType);
    }

    public Page<PackageTypeResponse> findAllPackageTypes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var packageTypes = packageTypeRepository.findAll(pageable);
        return packageTypeMapper.toPackageTypePageResponse(packageTypes);
    }

    public PackageTypeResponse findPackageTypeById(long id) {
        var packageType = packageTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PACKAGE_TYPE_NOT_FOUND));
        return packageTypeMapper.toPackageTypeResponse(packageType);
    }

    public PackageTypeResponse updatePackageTypeById(long id, PackageTypeRequest request) {
        var packageType = packageTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PACKAGE_TYPE_NOT_FOUND));
        packageType = packageTypeMapper.toPackageType(request);
        packageTypeRepository.save(packageType);
        return packageTypeMapper.toPackageTypeResponse(packageType);
    }

    public PackageTypeResponse deletePackageTypeById(long id) {
        var packageType = packageTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PACKAGE_TYPE_NOT_FOUND));
        packageTypeRepository.delete(packageType);
        return packageTypeMapper.toPackageTypeResponse(packageType);
    }

}
