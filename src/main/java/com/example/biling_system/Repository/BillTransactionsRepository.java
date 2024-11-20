package com.example.biling_system.Repository;

import com.example.biling_system.dto.response.BillTransactionsResponse;
import com.example.biling_system.model.BillTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BillTransactionsRepository extends JpaRepository<BillTransactions, Long> {

    @Query("SELECT bt FROM BillTransactions bt " +
            "WHERE (:packageName IS NULL OR bt.packageName LIKE CONCAT('%', :packageName, '%')) " +
            "AND (:packageCode IS NULL OR bt.packageCode LIKE CONCAT('%', :packageCode, '%')) " +
            "AND (:transactionDate IS NULL OR bt.transactionDate = :transactionDate)")
    List<BillTransactions> findBillTransactionsByCondition(@Param("packageName") String packageName,
                                                           @Param("packageCode") String packageCode,
                                                           @Param("transactionDate") Date transactionDate);




}