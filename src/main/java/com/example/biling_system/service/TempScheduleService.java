package com.example.biling_system.service;

import com.example.biling_system.Repository.TempScheduleRepository;
import com.example.biling_system.model.TempSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TempScheduleService {
    private final TempScheduleRepository tempScheduleRepository;
    public void createTempSchedule(TempSchedule tempSchedule) {
        tempScheduleRepository.save(tempSchedule);
    }
    public List<TempSchedule> getAllTempScheduleWhenStatusNotDone() {
       return tempScheduleRepository.findAllByStatus((byte)0);
    }
}
