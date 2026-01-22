package com.example.hospital.practice.dto;

import com.example.hospital.practice.entity.Rooms;
import com.example.hospital.practice.entity.utils.Gender;
import com.example.hospital.practice.entity.utils.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeDto {

    private Long id;

    private LocalDate doj;

    private BigInteger phoneNo;

    private Integer age;

    private String name;

    private Gender gender;

    private Double salary;

    private Role role;
}
