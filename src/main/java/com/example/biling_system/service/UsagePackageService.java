package com.example.biling_system.service;


import com.example.biling_system.Repository.UsagePackageRepository;
import com.example.biling_system.dto.request.UsagePackageRequest;
import com.example.biling_system.dto.response.UsagePackageResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.UsagePackageMapper;
import com.example.biling_system.model.PackageType;
import com.example.biling_system.model.Subcriber;
import com.example.biling_system.model.UsagePackage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsagePackageService {
    UsagePackageRepository usagePackageRepository;
    UsagePackageMapper usagePackageMapper;
    BillService billService;
    @PersistenceContext
    private EntityManager entityManager;

    public UsagePackageResponse createUsagePackage(UsagePackageRequest request) {
        UsagePackage usagePackage = usagePackageMapper.toUsagePackage(request);
        PackageType packageType = entityManager.find(PackageType.class, request.getIdPackageType());
        if (packageType == null) {
            throw new AppException(ErrorCode.PACKAGE_TYPE_NOT_FOUND);
        }
        usagePackage.setIdPackageType(packageType);
        usagePackageRepository.save(usagePackage);
        return usagePackageMapper.toUsagePackageResponse(usagePackage);
    }

    public Page<UsagePackageResponse> findAllUsagePackages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var usagepackage = usagePackageRepository.findAll(pageable);
        return usagePackageMapper.toUsagePackagePageResponse(usagepackage);
    }

    public List<UsagePackageResponse> getUsagePackagesWithinTimeRange(Date cronTime) {
        List<UsagePackage> listUsagePacke = usagePackageRepository.findTimeBetweenStartAndEnd(cronTime);
        List<UsagePackageResponse> responseList =
                usagePackageMapper.toUsagePackageResponseList(listUsagePacke);
        billService.createBillCrontab(responseList,cronTime);
        return responseList;
    }

    public UsagePackageResponse findUsagePackageById(long id) {
        var usagepackage = usagePackageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
        return usagePackageMapper.toUsagePackageResponse(usagepackage);
    }

    public UsagePackageResponse updateUsagePackage(long id, UsagePackageRequest request) {
        var usagepackage = usagePackageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
        usagepackage = usagePackageMapper.toUsagePackage(request);
        usagePackageRepository.save(usagepackage);
        return usagePackageMapper.toUsagePackageResponse(usagepackage);
    }

    public UsagePackageResponse deleteUsagePackage(long id) {
        var usagepackage = usagePackageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
        usagePackageRepository.delete(usagepackage);
        return usagePackageMapper.toUsagePackageResponse(usagepackage);
    }
}
