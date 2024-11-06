//package com.example.biling_system.service;
//
//
//import com.example.biling_system.Repository.PackageTypeRepository;
//import com.example.biling_system.dto.request.PackageTypeRequest;
//import com.example.biling_system.mapper.PackageTypeMapper;
//import com.example.biling_system.model.PackageType;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class PackageTypeService {
//
//    PackageTypeRepository packageTypeRepository;
//    PackageTypeMapper packageTypeMapper;
//
//    public PackageType createPackageType(PackageTypeRequest request) {
//        PackageType packageType = new PackageType();
//        packageType = packageTypeMapper.toPackageType(request);
//        return packageType;
//    }
//
//}
