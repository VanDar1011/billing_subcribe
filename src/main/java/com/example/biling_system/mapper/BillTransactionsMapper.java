package com.example.biling_system.mapper;

import com.example.biling_system.dto.response.BillTransactionsResponse;
import com.example.biling_system.model.BillTransactions;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BillTransactionsMapper {



    List<BillTransactionsResponse> toBillTransactions(List<BillTransactions> billTransactions);
}
