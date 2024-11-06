package com.example.biling_system.controller;


import com.example.biling_system.dto.CustomerDTO;
import com.example.biling_system.dto.request.CustomerRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.CustomerResponse;
import com.example.biling_system.model.Customer;
import com.example.biling_system.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerService customerService;

    @PostMapping
    public ApiResponse<CustomerResponse> addCustomer(@RequestBody CustomerRequest request) {
        ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
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

    @GetMapping ("/search")
    public ResponseEntity<ApiResponse<CustomerDTO>> searchByCCCd(@Param("cccd") String cccd) {
        ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();
        Optional<Customer> customer = customerService.searchCustomerByCCCD(cccd);
        if (customer.isPresent()) {
            Customer custom = customer.get();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setAddress(custom.getAddress());
            customerDTO.setName(custom.getName());
            customerDTO.setEmail(custom.getEmail());
            customerDTO.setGender(custom.getGender());
            customerDTO.setIdentifyCode(custom.getIdentifyCode());
            customerDTO.setCodeCus(custom.getCodeCus());
            customerDTO.setDateOfBirth(custom.getDateOfBirth());
            apiResponse.setData(customerDTO);
            apiResponse.setMessage("Customer found");
        }
        return ResponseEntity.status(200).body(apiResponse);
    }
}
