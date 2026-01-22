package com.example.hospital.practice.entity;

import com.example.hospital.practice.entity.utils.Gender;
import com.example.hospital.practice.entity.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate doj;

    @Column(nullable = false)
    private BigInteger phoneNo;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Double salary;

    @OneToMany(mappedBy = "manager")
    private Set<Rooms> managedRooms = new HashSet<>();

    @Column(nullable = false)
    private Role role;
}
