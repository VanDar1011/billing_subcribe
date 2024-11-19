package com.example.biling_system.Repository;

import com.example.biling_system.model.UsagePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsagePackageRepository extends JpaRepository<UsagePackage, Long> {
    @Query("""
           SELECT u FROM UsagePackage u WHERE u.startDay < :crobTime AND u.endDay > :crobTime AND u.note = 'NOT_BILL'
           """)
    List<UsagePackage> findTimeBetweenStartAndEnd(@Param("crobTime") Date crobTime);
}
