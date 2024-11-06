package com.example.biling_system.service;


import com.example.biling_system.Repository.SubcriberRepository;
import com.example.biling_system.dto.request.SubcriberRequest;
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

    public Subcriber create(SubcriberRequest request) {
        Subcriber subcriber = subcriberMapper.toSubcriber(request);
        return subcriberRepository.save(subcriber);
    }

    public Page<Subcriber> findAllSubcribers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subcriberRepository.findAll(pageable);
    }

    public Subcriber findSubcriberById(long id) {
        return subcriberRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUBCRIBER_NOT_FOUND));
    }

    public Subcriber updateSubcriber(long id, SubcriberRequest request) {
        Subcriber subcriber = findSubcriberById(id);
        subcriber = subcriberMapper.toSubcriber(request);
        return subcriberRepository.save(subcriber);
    }

    public Subcriber deleteSubcriber(long id) {
        Subcriber subcriber = findSubcriberById(id);
        subcriberRepository.delete(subcriber);
        return subcriber;
    }
}
