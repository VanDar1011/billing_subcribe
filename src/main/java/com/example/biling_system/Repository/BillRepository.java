package com.example.biling_system.Repository;

import com.example.biling_system.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findBillByBillCode(String billCode);
}
