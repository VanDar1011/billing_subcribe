package com.example.biling_system.mapper;


import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.dto.request.UsagePackageRequest;
import com.example.biling_system.dto.response.CustomerResponse;
import com.example.biling_system.dto.response.UsagePackageResponse;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.PackageType;
import com.example.biling_system.model.Subcriber;
import com.example.biling_system.model.UsagePackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsagePackageMapper {


    @Mapping(target = "idPackageType", source = "idPackageType")
    UsagePackage toUsagePackage(UsagePackageRequest request);
//
//    @Mapping(target = "IDsubcriber" , source = "IDsubcriber" )
    @Mapping(target = "idPackageType" , source = "idPackageType" )
    UsagePackageResponse toUsagePackageResponse(UsagePackage usagePackage);

    default List<UsagePackageResponse> toUsagePackageResponseList(List<UsagePackage> list) {
        return list.stream().map(this::toUsagePackageResponse).collect(Collectors.toList());
    }

    default Page<UsagePackageResponse> toUsagePackagePageResponse(Page<UsagePackage> usagePackages) {
        return usagePackages.map(this::toUsagePackageResponse);
    }

    default Long map(Subcriber subcriber) {
        return subcriber != null ? subcriber.getId() : null;
    }

    default Subcriber maptoSubcriber(long idSubcriber) {
        Subcriber subcriber = new Subcriber();
        subcriber.setId(idSubcriber);
        return subcriber ;
    }

    default Long map(PackageType packageType) {
        return packageType != null ? packageType.getId() : null;
    }

    default PackageType maptoPackageType(Long idPackageType) {
        PackageType packageType = new PackageType();
        packageType.setId(idPackageType);
        return packageType;

    }

    void updateUsage(@MappingTarget UsagePackage usagePackage, UsagePackageRequest request);


}
