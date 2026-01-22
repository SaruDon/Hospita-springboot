package com.example.hospital.practice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;

    @OneToMany(mappedBy = "bill" , cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<PaymentTransactions> paymentTransactionsSet = new HashSet<>();
}
