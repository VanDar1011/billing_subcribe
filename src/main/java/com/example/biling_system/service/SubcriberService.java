package com.example.biling_system.service;


import com.example.biling_system.Repository.SubcriberRepository;
import com.example.biling_system.dto.request.SubcriberRequest;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.mapper.SubcriberMapper;
import com.example.biling_system.model.Subcriber;
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
public class SubcriberService {
    SubcriberRepository subcriberRepository;
    SubcriberMapper subcriberMapper;

    public SubcriberResponse create(SubcriberRequest request) {
        var subcriber = subcriberMapper.toSubcriber(request);
        subcriber = subcriberRepository.save(subcriber);
        return subcriberMapper.toSubcriberResponse(subcriber);
    }

    public Page<SubcriberResponse> findAllSubcribers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var subcribers = subcriberRepository.findAll(pageable);
        return subcriberMapper.toCustomerResponsePage(subcribers);
    }

    public SubcriberResponse findSubcriberById(long id) {
        var subcriber = subcriberRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUBCRIBER_NOT_FOUND));
        return subcriberMapper.toSubcriberResponse(subcriber);
    }

    public SubcriberResponse updateSubcriber(long id, SubcriberRequest request) {
        var subcriber = subcriberRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUBCRIBER_NOT_FOUND));
        subcriber = subcriberMapper.toSubcriber(request);
        subcriberRepository.save(subcriber);
        return subcriberMapper.toSubcriberResponse(subcriber);
    }

    public SubcriberResponse deleteSubcriber(long id) {
        var subcriber = subcriberRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUBCRIBER_NOT_FOUND));
        subcriberRepository.delete(subcriber);
        return subcriberMapper.toSubcriberResponse(subcriber);
    }
}
