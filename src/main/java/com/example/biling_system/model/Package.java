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
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_package")
    int id;
    @Column(length = 10, name = "package_code", unique = true)
    String packageCode;
    @Column(length = 50, name = "package_name")
    String packageName;
    @Column(length = 50, name = "package_capacity")
    String packageCapacity;
    @Column(name = "package_price")
    Double packagePrice;

    @OneToMany(mappedBy = "idPkg", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsagePackage> usagePackages;



}
