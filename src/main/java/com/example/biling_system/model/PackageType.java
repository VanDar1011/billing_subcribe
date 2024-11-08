package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "packages")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_package_type")
    long id;
    @Column(length = 10, name = "package_type_code", unique = true)
    String packageCode;
    @Column(length = 50, name = "package_type_name")
    String packageName;
    @Column(length = 50, name = "package_type_capacity")
    String packageCapacity;
    @Column(name = "package_type_price")
    Double packagePrice;

    @OneToMany(mappedBy = "idPackageType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UsagePackage> usagePackages;


}
