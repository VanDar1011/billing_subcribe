package com.example.biling_system.mapper;

import com.example.biling_system.dto.request.PackageTypeRequest;
import com.example.biling_system.model.PackageType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PackageTypeMapper {
    PackageType toPackageType(PackageTypeRequest request);
}
