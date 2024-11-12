package com.example.biling_system.mapper;
import com.example.biling_system.dto.CustomerDTO;
import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.dto.response.CustomerResponse;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.Subcriber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "subcribers", ignore = true)
    Customer toCustomer(CustomerRequest request);

    @Mapping(target = "subcribers", source = "subcribers")
    CustomerResponse toCustomerResponse(Customer customer);

    default List<String> toStringList(List<Subcriber> list) {
        return list != null ? list.stream()
                .map(Subcriber::getPhoneNumber)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    default List<CustomerResponse> toCustomerResponseList(List<Customer> list) {
        return list.stream().map(this::toCustomerResponse).collect(Collectors.toList());
    }

    default Page<CustomerResponse> toCustomerResponsePage(Page<Customer> customers) {
        return customers.map(this::toCustomerResponse);
    }


    void updateCustomer(@MappingTarget Customer customer, CustomerRequest request);



}
