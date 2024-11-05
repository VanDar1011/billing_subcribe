package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Table(name="bills")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 10,name="bill_code",unique=true)
    String billCode;
    @Column(length = 20,name="customer_code")
    String customerCode;
    @Column(length = 10,name="subcibe_code")
    String subcibeCode;
    @Column(length = 10,name="usage_package_code")
    String usagePackageCode;
    @Column(name="establishtment_day")
    Date establishtmentDay;
    @Column(name="collect_day")
    Date collectionDay;
    @Column(name="total_amount")
    Double  totalAmount;
    @Column(length = 20)
    String status;




}
