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
    @Column(name = "id_packagetype")
    long id;
    @Column(length = 10, name = "packagetype_code", unique = true)
    String packageCode;
    @Column(length = 50, name = "packagetype_name")
    String packageName;
    @Column(length = 50, name = "packagetype_capacity")
    String packageCapacity;
    @Column(name = "packagetype_price")
    Double packagePrice;

    @OneToMany(mappedBy = "idPackageType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsagePackage> usagePackages;



}
