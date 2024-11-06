package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Table(name = "usage_packages")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsagePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usagepackage")
    long id;
    @Column(name = "start_day")
    Date startDay;
    @Column(name = "end_day")
    Date endDay;
    @Column(length = 20, name = "check_status")
    String checkoutStatus;
    @Column(length = 20, name = "Note")
    String note;

    @ManyToOne
    @JoinColumn(name = "id_subcriber", referencedColumnName = "id_phonenumber")
    Subcriber IDsubcriber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_packagetype", referencedColumnName = "id_packagetype")
    PackageType idPackageType;


}
