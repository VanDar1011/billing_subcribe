package com.example.biling_system.model;

import jakarta.persistence.*;

@Entity
@Table(name="bills")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
