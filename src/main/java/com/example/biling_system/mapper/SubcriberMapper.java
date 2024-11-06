package com.example.biling_system.mapper;


import com.example.biling_system.dto.SubcriberDTO;
import com.example.biling_system.dto.request.SubcriberRequest;
import com.example.biling_system.dto.response.CustomerResponse;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.Subcriber;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SubcriberMapper {
    Subcriber toSubcriber(SubcriberRequest subcriber);

    List<SubcriberDTO> toSubcriberDTOList(List<Subcriber> subcribers);

    SubcriberResponse toSubcriberResponse(Subcriber subcriber);

    default Page<SubcriberResponse> toCustomerResponsePage(Page<Subcriber> subcribers) {
        return subcribers.map(this::toSubcriberResponse);
    }
}
