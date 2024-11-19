package com.example.biling_system.mapper;

import com.example.biling_system.dto.TempSchedueDTO;
import com.example.biling_system.model.TempSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TempScheduleMapper {
    TempSchedueDTO toTempScheduleDTO(TempSchedule tempSchedule);
}
