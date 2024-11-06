package com.example.biling_system.service;


import com.example.biling_system.Repository.CustomerRepository;
import com.example.biling_system.dto.request.CustomerRequest;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public Customer createCustomer(CustomerRequest request) {

        Customer customer = customerMapper.toCustomer(request);

        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
    }

    public Page<Customer> findAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable);
    }

    public Customer updateCustomer(Long id, CustomerRequest request) {
        Customer customer = findCustomerById(id);
        customer = customerMapper.toCustomer(request);
        return customerRepository.save(customer);
    }

    public Customer deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
        return customer;
    }
}
