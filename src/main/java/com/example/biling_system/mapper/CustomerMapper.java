package com.example.biling_system.mapper;
import com.example.biling_system.dto.CustomerDTO;
import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.dto.response.CustomerResponse;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.Subcriber;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest request);

    CustomerResponse toCustomerResponse(Customer customer);

    default List<String> toStringList(List<Subcriber> list) {
        return list.stream()
                .map(Subcriber::getPhoneNumber)
                .collect(Collectors.toList());
    }

    default Page<CustomerResponse> toCustomerResponsePage(Page<Customer> customers) {
        return customers.map(this::toCustomerResponse);
    }



}
