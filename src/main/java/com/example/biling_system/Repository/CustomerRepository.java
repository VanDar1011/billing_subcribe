package com.example.biling_system.Repository;


import com.example.biling_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
//    @Query("SELECT c FROM Customer c WHERE c.identifyCode = :identifyCode")
    Customer findByIdentifyCode(String identifyCode);
    boolean existsByCodeCus(String customerCode);


}
