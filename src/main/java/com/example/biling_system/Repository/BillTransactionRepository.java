package com.example.biling_system.Repository;

import com.example.biling_system.model.BillTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillTransactionRepository  extends JpaRepository<BillTransactions, Long> {

}
