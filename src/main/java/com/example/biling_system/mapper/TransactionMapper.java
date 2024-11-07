package com.example.biling_system.mapper;
import com.example.biling_system.dto.response.TransactionResponse;
import com.example.biling_system.model.Transaction;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toResponse(Transaction transaction);
}
