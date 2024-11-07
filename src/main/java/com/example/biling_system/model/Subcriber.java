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
    @Column(name = "id_phonenumber")
    long id;
    @Column(length = 10, name = "CodeNumber")
    String codeNumber;
    @Column(length = 15, name = "PhoneNumber")
    String phoneNumber;
    @Column(length = 20, name = "PhoneNumberType")
    String phoneNumberType;
    @Column(name = "ActiveDay")
    LocalDate dayActive;
    @Column(name = "InactiveDay")
    LocalDate dayInactive;
    @Column(length = 20, name = "SeriPhoneNumber")
    String seriPhoneNumber;
    @Column(name = "Status")
    boolean status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCustomer", referencedColumnName = "id_customer")
    Customer idCustomer;
    @OneToMany(mappedBy = "IDsubcriber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsagePackage> usagePackages;

}
