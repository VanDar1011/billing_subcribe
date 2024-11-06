package com.example.biling_system.service;


import com.example.biling_system.Repository.CustomerRepository;
import com.example.biling_system.dto.CustomerDTO;
import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.dto.response.CustomerResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.CustomerMapper;
import com.example.biling_system.model.Customer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest request) {

        Customer customer = customerMapper.toCustomer(request);

        customer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponse findCustomerById(long id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        return customerMapper.toCustomerResponse(customer);
    }

    public Page<CustomerResponse> findAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var customer = customerRepository.findAll(pageable);
        return customerMapper.toCustomerResponsePage(customer);
    }

    public CustomerResponse updateCustomer(long id, CustomerRequest request) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        customer = customerMapper.toCustomer(request);
        customer = customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponse deleteCustomer(long id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        customerRepository.delete(customer);
        return customerMapper.toCustomerResponse(customer);
    }
    public CustomerResponse searchCustomerByCCCD(String cccd ) {
        Customer customer = customerRepository.findByIdentifyCode(cccd);
        if(customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return customerMapper.toCustomerResponse(customer);
    }

}
