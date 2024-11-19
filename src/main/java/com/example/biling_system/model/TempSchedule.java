package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import java.sql.Date;
@Entity
@Table(name = "temple_schedule")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TempSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String transactionCode;
    @Column(columnDefinition = "SMALLINT default 0")
    byte status;
    Date scheduleDate;
}
