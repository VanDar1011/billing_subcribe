package com.example.biling_system.mapper;
import com.example.biling_system.dto.request.TransactionRequest;
import com.example.biling_system.dto.response.TransactionResponse;
import com.example.biling_system.model.Bill;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


    @Mapping(target = "idBill", source = "idBill")
    Transaction toTransaction(TransactionRequest transactionRequest);

    @Mapping(target = "idBill", source = "idBill")
    TransactionResponse toResponse(Transaction transaction);

    default Long getIdBill(Bill bill) {
        return bill != null ? bill.getId() : null;
    }

    default Bill mapIdToCustomer(long idBill) {
        Bill bill = new Bill();
        bill.setId(idBill);
        return bill;
    }

    List<TransactionResponse> toResponseList(List<Transaction> transactions);


}
