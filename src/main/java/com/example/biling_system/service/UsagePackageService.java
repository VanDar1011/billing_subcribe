package com.example.biling_system.service;



import com.example.biling_system.Repository.UsagePackageRepository;
import com.example.biling_system.dto.request.UsagePackageRequest;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.UsagePackageMapper;
import com.example.biling_system.model.UsagePackage;
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
public class UsagePackageService {
    UsagePackageRepository usagePackageRepository;
    UsagePackageMapper usagePackageMapper;

    public UsagePackage createUsagePackage(UsagePackageRequest request) {
        UsagePackage usagePackage = usagePackageMapper.toUsagePackage(request);
        return usagePackageRepository.save(usagePackage);
    }

    public Page<UsagePackage> findAllUsagePackages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usagePackageRepository.findAll(pageable);
    }

    public UsagePackage findUsagePackageById(long id) {
        return usagePackageRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USAGE_PACKAGE_NOT_FOUND));
    }

    public UsagePackage updateUsagePackage(long id, UsagePackageRequest request) {
        UsagePackage usagePackage = findUsagePackageById(id);
        usagePackage = usagePackageMapper.toUsagePackage(request);
        return usagePackageRepository.save(usagePackage);
    }

    public UsagePackage deleteUsagePackage(long id) {
        UsagePackage usagePackage = findUsagePackageById(id);
        usagePackageRepository.delete(usagePackage);
        return usagePackage;
    }
}
