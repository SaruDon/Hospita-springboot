package com.example.hospital.practice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private Set<Patient> patientSet= new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Employee manager;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer roomNo;

    @Column(nullable = false)
    private Boolean available=false;
}
