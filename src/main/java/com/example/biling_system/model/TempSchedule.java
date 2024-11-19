package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.sql.Date;
@Entity
@Table(name = "temple_schedule")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TempSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(length = 13, unique = true, nullable = false)
    String transactionCode;
    @Column(columnDefinition = "SMALLINT default 0")
    byte status;
}
