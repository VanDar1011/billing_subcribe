package com.example.biling_system.Repository;


import com.example.biling_system.model.Subcriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcriberRepository extends JpaRepository<Subcriber, Long> {

}
