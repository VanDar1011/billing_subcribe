package com.example.biling_system.Repository;

import com.example.biling_system.model.TempSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempScheduleRepository extends JpaRepository<TempSchedule, Long> {
    List<TempSchedule> findAllByStatus(byte status);
}
