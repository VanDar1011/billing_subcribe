package com.example.biling_system.service;


import com.example.biling_system.Repository.CustomerRepository;
import com.example.biling_system.Repository.SubcriberRepository;
import com.example.biling_system.dto.request.SubcriberRequest;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.SubcriberMapper;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.Subcriber;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubcriberService {
    SubcriberRepository subcriberRepository;
    SubcriberMapper subcriberMapper;
    CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public SubcriberResponse create(SubcriberRequest request) {

        var subcriber = subcriberMapper.toSubcriber(request);
        Customer customer = entityManager.find(Customer.class, request.getIdCustomer());
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        subcriber.setIdCustomer(customer);
        subcriber = subcriberRepository.save(subcriber);
        return subcriberMapper.toSubcriberResponse(subcriber);
    }


    public Page<SubcriberResponse> findAllSubcribers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var subcribers = subcriberRepository.findAll(pageable);
        return subcriberMapper.toCustomerResponsePage(subcribers);
    }

    public SubcriberResponse findSubcriberById(long id) {
        var subcriber = subcriberRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBCRIBER_NOT_FOUND));
        return subcriberMapper.toSubcriberResponse(subcriber);
    }

    @Transactional
    public SubcriberResponse updateSubcriber(long id, SubcriberRequest request) {
        var subcriber = subcriberRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBCRIBER_NOT_FOUND));

        subcriberMapper.updateSubcriber(subcriber, request);
        Customer customer = entityManager.find(Customer.class, request.getIdCustomer());
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        subcriber.setIdCustomer(customer);
        subcriberRepository.save(subcriber);
        return subcriberMapper.toSubcriberResponse(subcriber);
    }

    public SubcriberResponse deleteSubcriber(long id) {
        var subcriber = subcriberRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBCRIBER_NOT_FOUND));
        subcriberRepository.delete(subcriber);
        return subcriberMapper.toSubcriberResponse(subcriber);
    }

    public List<SubcriberResponse> searchSubcriberByCCCD(String cccd) {
        Customer customer = customerRepository.findByIdentifyCode(cccd);
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        List<SubcriberResponse> subcriberResponses =
                subcriberMapper.toSubcriberRespnoseList(customer.getSubcribers());
        return subcriberResponses;

    }

    public List<SubcriberResponse> searchSubcriberByPhoneNumber(String phoneNumber) {
        Subcriber subcriber = subcriberRepository.findByPhoneNumber(phoneNumber);
        if (subcriber == null) {
            throw new AppException(ErrorCode.SUBCRIBER_NOT_FOUND);
        }
        List<SubcriberResponse> subcriberResponses = new ArrayList<>();
        SubcriberResponse subcriberResponse = subcriberMapper.toSubcriberResponse(subcriber);
        subcriberResponses.add(subcriberResponse);
        return subcriberResponses;

    }
}
