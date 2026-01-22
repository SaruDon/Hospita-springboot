package com.example.hospital.practice.entity;

import com.example.hospital.practice.entity.utils.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigInteger phoneNo;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointmentSet= new HashSet<>();

}
