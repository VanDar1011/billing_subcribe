package com.example.biling_system.mapper;

import com.example.biling_system.dto.request.PackageTypeRequest;
import com.example.biling_system.dto.response.PackageTypeResponse;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.model.PackageType;
import com.example.biling_system.model.Subcriber;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PackageTypeMapper {
    PackageType toPackageType(PackageTypeRequest request);

    PackageTypeResponse toPackageTypeResponse(PackageType packageType);

    default Page<PackageTypeResponse> toPackageTypePageResponse(Page<PackageType> packageTypes) {
        return packageTypes.map(this::toPackageTypeResponse);
    }
}
