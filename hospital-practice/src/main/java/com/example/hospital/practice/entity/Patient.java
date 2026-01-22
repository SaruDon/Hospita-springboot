package com.example.hospital.practice.entity;

import com.example.hospital.practice.entity.utils.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private BigInteger phoneNo;


    private Integer age;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime admittedDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Appointment>appointments = new HashSet<>();


    @ManyToOne()
    private Rooms room;

    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TestReports> testReports= new HashSet<>();

    @OneToOne(mappedBy = "patient")
    private Bills bill;

}
