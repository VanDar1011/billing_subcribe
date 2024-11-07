package com.example.biling_system.controller;


import com.example.biling_system.Repository.SubcriberRepository;
import com.example.biling_system.dto.SubcriberDTO;
import com.example.biling_system.dto.request.SubcriberRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.service.SubcriberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcribers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubcriberController {
    SubcriberService subcriberService;
    SubcriberRepository subcriberRepository;

    @PostMapping
    public ApiResponse<SubcriberResponse> create(@RequestBody SubcriberRequest request) {
        boolean exists = subcriberRepository.existsByCodeNumber(request.getCodeNumber())
                || subcriberRepository.existsByPhoneNumber(request.getPhoneNumber());
        if (exists){
            throw new AppException(ErrorCode.PHONE_EXIST);
        }
        ApiResponse<SubcriberResponse> apiResponse = new ApiResponse<>();
        var subcriber = subcriberService.create(request);
        apiResponse.setData(subcriber);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<SubcriberResponse>> getAllSubcribers(@RequestParam(defaultValue = "0") int page, @RequestParam
            (defaultValue = "10") int size) {
        ApiResponse<List<SubcriberResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(subcriberService.findAllSubcribers(page, size).getContent());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<SubcriberResponse> getSubcriberById(@PathVariable("id") long id) {
        ApiResponse<SubcriberResponse> apiResponse = new ApiResponse<>();
        var subcriber = subcriberService.findSubcriberById(id);
        apiResponse.setData(subcriber);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<SubcriberResponse> updateSubcriberById(@PathVariable("id") long id, @RequestBody SubcriberRequest request) {
        ApiResponse<SubcriberResponse> apiResponse = new ApiResponse<>();
        var subcriber = subcriberService.updateSubcriber(id, request);
        apiResponse.setData(subcriber);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<SubcriberResponse> deleteSubcriberById(@PathVariable("id") long id) {
        ApiResponse<SubcriberResponse> apiResponse = new ApiResponse<>();
        var subcriber = subcriberService.deleteSubcriber(id);
        if (subcriber != null) {
            apiResponse.setData(subcriber);
        }
        return apiResponse;
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SubcriberDTO>>> searchSubcriber(@Param("cccd") String cccd){
        ApiResponse<List<SubcriberDTO>> apiResponse = new ApiResponse<>();
        List<SubcriberDTO> listSubcriber = subcriberService.searchSubcriberByCCC(cccd);

        apiResponse.setData(listSubcriber);
        return ResponseEntity.status(200).body(apiResponse);
    }

}
