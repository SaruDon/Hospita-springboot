package com.example.hospital.practice.dto;

import com.example.hospital.practice.entity.Appointment;
import com.example.hospital.practice.entity.Bills;
import com.example.hospital.practice.entity.Rooms;
import com.example.hospital.practice.entity.TestReports;
import com.example.hospital.practice.entity.utils.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class PatientDto {

    private Long id;

    private LocalDate dob;

    private BigInteger phoneNo;

    private Integer age;

    private String name;

    private Gender gender;

    private LocalDateTime admittedDate;

}
