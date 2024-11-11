package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    long id;

    @Column(length = 20, name = "code_customer", unique = true)
    String codeCus;
    @Column(length = 50, name = "name_customer")
    String name;
    @Column(name = " date_of_birth")
    LocalDate dateOfBirth;
    @Column(length = 10, name = "sex")
    String gender;
    @Column(length = 100, name ="address")
    String address;
    @Column(length = 100, name = "email")
    String email;
    @Column(length = 12, name = "identify_code")
    String identifyCode;
    @OneToMany(mappedBy = "idCustomer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Subcriber> subcribers;
}

