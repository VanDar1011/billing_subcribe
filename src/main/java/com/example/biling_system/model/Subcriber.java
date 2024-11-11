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
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "phoneNumbers")
public class Subcriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone_number")
    long id;
    @Column(length = 10, name = "code_number")
    String codeNumber;
    @Column(length = 15, name = "phone_number")
    String phoneNumber;
    @Column(length = 20, name = "phone_number_type")
    String phoneNumberType;
    @Column(name = "active_day")
    LocalDate dayActive;
    @Column(name = "inactive_day")
    LocalDate dayInactive;
    @Column(length = 20, name = "seri_phone_number")
    String seriPhoneNumber;
    @Column(name = "status")
    boolean status;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer")
    Customer idCustomer;
    @OneToMany(mappedBy = "IDsubcriber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsagePackage> usagePackages;

}
