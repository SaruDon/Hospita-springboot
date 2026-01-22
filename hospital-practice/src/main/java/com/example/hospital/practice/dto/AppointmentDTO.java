package com.example.hospital.practice.dto;

import com.example.hospital.practice.entity.Doctor;
import com.example.hospital.practice.entity.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private Long id;

    private Patient patient;

    private Doctor doctor;

    private LocalDateTime localDateTime;
}
