package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Customer")
    int id;

    @Column(length = 20, name = "CodeCustomer", unique = true)
    String codeCus;
    @Column(length = 50, name = "NameCustomer")
    String name;
    @Column(name = " DateOfBirth")
    LocalDate dateOfBirth;
    @Column(length = 10, name = "Sex")
    String gender;
    @Column(length = 100, name ="Address")
    String address;
    @Column(length = 100, name = "Email")
    String email;
    @Column(length = 12, name = "IdentifyCode")
    String identifyCode;
    @OneToMany(mappedBy = "idCustomer", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Subcriber> subcribers;
}

