package com.example.hospital.practice.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctor doctor;

    private LocalDateTime  localDateTime;
}
