package com.example.biling_system.controller;


import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.model.Customer;
import com.example.biling_system.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    @PostMapping
    public ApiResponse<Customer> addCustomer(@RequestBody CustomerRequest request) {
        ApiResponse<Customer> apiResponse = new ApiResponse<>();
        apiResponse.setData(customerService.createCustomer(request));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<Customer>> apiResponse = new ApiResponse<>();
        apiResponse.setData(customerService.findAllCustomers(page, size).getContent());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<Customer> getCustomer(@PathVariable("id") long id) {
        ApiResponse<Customer> apiResponse = new ApiResponse<>();
        apiResponse.setData(customerService.findCustomerById(id));
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerRequest request) {
        ApiResponse<Customer> apiResponse = new ApiResponse<>();
        apiResponse.setData(customerService.updateCustomer(id, request));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Customer> deleteCustomer(@PathVariable("id") long id) {

        ApiResponse<Customer> apiResponse = new ApiResponse<>();
        Customer customer = customerService.deleteCustomer(id);
        if (customer != null) {
            apiResponse.setData(customer);
        }
        return apiResponse;
    }
}
