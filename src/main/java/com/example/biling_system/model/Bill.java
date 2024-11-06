package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Table(name = "bills")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
    int id;
    @Column(length = 10, name = "bill_code", unique = true)
    String billCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer")
    Customer idCustomer;
    @Column(length = 10, name = "subcibe_code")
    String subcibeCode;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usage_package_id", referencedColumnName = "id_usagepackage")
    UsagePackage usagePackageCode;
    @Column(name = "establishtment_day")
    Date establishtmentDay;
    @Column(name = "collect_day")
    Date collectionDay;
    @Column(name = "total_amount")
    Double totalAmount;
    @Column(length = 20)
    String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsagePackage", referencedColumnName = "id_usagepackage")
    UsagePackage idUsagePackage;


}
