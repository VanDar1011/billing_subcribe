package com.example.biling_system.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Table(name = "transactions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    int id;
    @Column(length = 10, name = "transaction_code", unique = true)
    String transactionCode;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id", referencedColumnName = "id_bill")
    Bill idBill;
    @Column(name = "transaction_date")
    Date transactionDate;
    @Column(name = "total_amount")
    Double totalAmount;
    @Column(length = 20, name = "status")
    String status;

}
