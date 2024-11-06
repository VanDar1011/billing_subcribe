package com.example.biling_system.Repository;

import com.example.biling_system.model.UsagePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagePackageRepository extends JpaRepository<UsagePackage, Long> {

}
