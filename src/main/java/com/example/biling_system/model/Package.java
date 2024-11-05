package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="packages")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 10,name = "package_code",unique=true)
    String packageCode;
    @Column(length = 50,name="package_name")
    String packageName;
    @Column(length = 50,name="package_capacity")
    String packageCapacity;
    @Column(name="package_price")
    Double packagePrice;

}
