package com.example.biling_system.mapper;


import com.example.biling_system.dto.request.UsagePackageRequest;
import com.example.biling_system.model.UsagePackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsagePackageMapper {
    UsagePackage toUsagePackage(UsagePackageRequest request);
}
