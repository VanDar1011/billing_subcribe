package com.example.biling_system.mapper;
import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.model.Customer;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest request);

}
