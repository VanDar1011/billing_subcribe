package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Table(name = "bill_transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(length = 13, unique = true)
    String transactionCode;
    @Column(length = 20, name = "code_customer", unique = true)
    String codeCus;
    @Column(length = 50)
    String nameCustomer;
    @Column(length = 12)
    String identifyCode;
    @Column(length = 10)
    String gender_customer;
    @Column(length = 100)
    String address;
    @Column(length = 10)
    String codeNumber;
    @Column(length = 15)
    String phoneNumber;
    @Column(length = 20)
    String phoneNumberType;
    @Column(length = 10, name = "package_type_code")
    String packageCode;
    @Column(length = 50, name = "package_type_name")
    String packageName;
    @Column(length = 50, name = "package_type_capacity")
    String packageCapacity;
    @Column
    Date startDay;
    @Column
    Date endDay;
    @Column
    Double totalAmount;
    @Column
    Date transactionDate;

}
