package com.example.biling_system.service;


import com.example.biling_system.Repository.BillRepository;
import com.example.biling_system.Repository.UsagePackageRepository;
import com.example.biling_system.dto.request.UsagePackageRequest;
import com.example.biling_system.dto.response.UsagePackageResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.UsagePackageMapper;
import com.example.biling_system.model.Bill;
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
import org.springframework.transaction.annotation.Transactional;

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
    EntityManager entityManager;
    BillRepository billRepository;

    public UsagePackageResponse createUsagePackage(UsagePackageRequest request) {
        var usagePackage = usagePackageMapper.toUsagePackage(request);
        Subcriber subcriber = entityManager.find(Subcriber.class, request.getIdSubcriber());
        if (subcriber == null) {
            throw new AppException(ErrorCode.SUBCRIBER_NOT_FOUND);
        }
        usagePackage.setIdSubcriber(subcriber);
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
        billService.createBillCrontab(responseList, cronTime);
        return responseList;
    }

    public UsagePackageResponse findUsagePackageById(long id) {
        var usagepackage = usagePackageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
        return usagePackageMapper.toUsagePackageResponse(usagepackage);
    }

    @Transactional
    public UsagePackageResponse updateUsagePackage(long id, UsagePackageRequest request) {
        var usagepackage = usagePackageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
        usagePackageMapper.updateUsage(usagepackage, request);
        Subcriber subcriber = entityManager.find(Subcriber.class, request.getIdSubcriber());
        if (subcriber == null) {
            throw new AppException(ErrorCode.SUBCRIBER_NOT_FOUND);
        }
        usagepackage.setIdSubcriber(subcriber);
        PackageType packageType = entityManager.find(PackageType.class, request.getIdPackageType());
        if (packageType == null) {
            throw new AppException(ErrorCode.PACKAGE_TYPE_NOT_FOUND);
        }
        usagepackage.setIdPackageType(packageType);
        usagePackageRepository.save(usagepackage);
        return usagePackageMapper.toUsagePackageResponse(usagepackage);
    }

    public UsagePackageResponse deleteUsagePackage(long id) {
        var usagepackage = usagePackageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
        usagePackageRepository.delete(usagepackage);
        return usagePackageMapper.toUsagePackageResponse(usagepackage);
    }

    @Transactional
    public void updateStatusUsagePackage(long idUsagepackage) {
        UsagePackage usagepackage = usagePackageRepository.findById(idUsagepackage)
                .orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
        Bill bill = billRepository.findByIdUsagePackage(idUsagepackage);

        if (bill != null) {
            String billStatus = bill.getStatus();
            if ("completed".equals(billStatus)) {
                usagepackage.setCheckoutStatus("WORKING");
            }
            if ("pending".equals(billStatus)) {
                usagepackage.setCheckoutStatus("PENDING");
            }
            usagePackageRepository.save(usagepackage);
        } else throw new AppException(ErrorCode.BILL_NOT_FOUND);
    }
}
