package com.example.biling_system.controller;


import com.example.biling_system.dto.request.SubcriberRequest;
import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.model.Subcriber;
import com.example.biling_system.service.SubcriberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcribers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubcriberController {
    SubcriberService subcriberService;

    @PostMapping
    public ApiResponse<Subcriber> create(@RequestBody SubcriberRequest request) {
        ApiResponse<Subcriber> apiResponse = new ApiResponse<>();
        Subcriber subcriber = subcriberService.create(request);
        apiResponse.setData(subcriber);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<Subcriber>> getAllSubcribers(@RequestParam(defaultValue = "0") int page, @RequestParam
            (defaultValue = "10") int size) {
        ApiResponse<List<Subcriber>> apiResponse = new ApiResponse<>();
        apiResponse.setData(subcriberService.findAllSubcribers(page, size).getContent());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<Subcriber> getSubcriberById(@PathVariable("id") long id) {
        ApiResponse<Subcriber> apiResponse = new ApiResponse<>();
        Subcriber subcriber = subcriberService.findSubcriberById(id);
        apiResponse.setData(subcriber);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<Subcriber> updateSubcriberById(@PathVariable("id") long id, @RequestBody SubcriberRequest request) {
        ApiResponse<Subcriber> apiResponse = new ApiResponse<>();
        Subcriber subcriber = subcriberService.updateSubcriber(id, request);
        apiResponse.setData(subcriber);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Subcriber> deleteSubcriberById(@PathVariable("id") long id) {
        ApiResponse<Subcriber> apiResponse = new ApiResponse<>();
        Subcriber subcriber = subcriberService.deleteSubcriber(id);
        if (subcriber != null) {
            apiResponse.setData(subcriber);
        }
        return apiResponse;
    }

}
