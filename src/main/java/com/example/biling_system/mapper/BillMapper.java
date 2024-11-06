package com.example.biling_system.mapper;

import com.example.biling_system.dto.request.BillRequest;
import com.example.biling_system.model.Bill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toBill(BillRequest request);

}
