package com.example.biling_system.mapper;

import com.example.biling_system.dto.request.BillRequest;
import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.dto.response.BillResponse;
import com.example.biling_system.model.Bill;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.UsagePackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface BillMapper {


    @Mapping(target = "idUsagePackage", source = "idUsagePackage")
    Bill toBill(BillRequest request);


    @Mapping(target = "idUsagePackage", source = "idUsagePackage")
    BillResponse toBillResponse(Bill bill);


    default Page<BillResponse> toBillResponsePage(Page<Bill> bills) {
        return bills.map(this::toBillResponse);
    }


    default UsagePackage mapToUsagePackage(Long idUsagePackage) {
        if (idUsagePackage == null) return null;
        UsagePackage usagePackage = new UsagePackage();
        usagePackage.setId(idUsagePackage);
        return usagePackage;
    }

    default Long map(UsagePackage usagePackage) {
        return usagePackage != null ? usagePackage.getId() : null;
    }

    void updateBill(@MappingTarget Bill bill, BillRequest request);
}
