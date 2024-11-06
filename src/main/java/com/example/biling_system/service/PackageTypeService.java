package com.example.biling_system.service;


import com.example.biling_system.Repository.PackageTypeRepository;
import com.example.biling_system.dto.request.PackageTypeRequest;
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

    public PackageType createPackageType(PackageTypeRequest request) {
        PackageType packageType = packageTypeMapper.toPackageType(request);
        packageTypeRepository.save(packageType);
        return packageType;
    }

    public Page<PackageType> findAllPackageTypes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return packageTypeRepository.findAll(pageable);
    }

    public PackageType findPackageTypeById(long id) {
        return packageTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PACKAGE_TYPE_NOT_FOUND));
    }

    public PackageType updatePackageTypeById(long id, PackageTypeRequest request) {
        PackageType packageType = findPackageTypeById(id);
        packageType = packageTypeMapper.toPackageType(request);
        packageTypeRepository.save(packageType);
        return packageType;
    }

    public PackageType deletePackageTypeById(long id) {
        PackageType packageType = findPackageTypeById(id);
        packageTypeRepository.delete(packageType);
        return packageType;
    }

}
