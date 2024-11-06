package com.example.biling_system.mapper;


import com.example.biling_system.dto.request.SubcriberRequest;
import com.example.biling_system.model.Subcriber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubcriberMapper {
    Subcriber toSubcriber(SubcriberRequest subcriber);
}
