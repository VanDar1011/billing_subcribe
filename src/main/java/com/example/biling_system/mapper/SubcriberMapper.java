package com.example.biling_system.mapper;


import com.example.biling_system.dto.SubcriberDTO;
import com.example.biling_system.dto.request.SubcriberRequest;
import com.example.biling_system.dto.response.CustomerResponse;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.model.Customer;
import com.example.biling_system.model.Subcriber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SubcriberMapper {

    @Mapping(target = "idCustomer", source = "idCustomer")
    Subcriber toSubcriber(SubcriberRequest subcriber);

    List<SubcriberDTO> toSubcriberDTOList(List<Subcriber> subcribers);
    List<SubcriberResponse> toSubcriberRespnoseList(List<Subcriber> subcribers);
    @Mapping(target = "idCustomer", source = "idCustomer")
    SubcriberResponse toSubcriberResponse(Subcriber subcriber);

    default Page<SubcriberResponse> toCustomerResponsePage(Page<Subcriber> subcribers) {
        return subcribers.map(this::toSubcriberResponse);
    }

    default Long map(Customer customer) {
        return customer != null ? customer.getId() : null;
    }

    default Customer mapIdToCustomer(long idCustomer) {
        Customer customer = new Customer();
        customer.setId(idCustomer);
        return customer;
    }



}
