package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Table(name = "usage_packages")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsagePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 10,name="usage_package_code",unique=true)
    String usagePackageCode;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "package_id",referencedColumnName = "id")
    Package packageObjs;
    Date startDay;
    Date endDay;
    @Column(length = 20)
    String checkout_status;
    String note;



}
