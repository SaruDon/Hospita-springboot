package com.example.hospital.practice.repository;

import com.example.hospital.practice.dto.AppointmentDTO;
import com.example.hospital.practice.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo  extends JpaRepository<Appointment,Long> {
    Page<Appointment> findByDoctorId(Long doctorId, Pageable pageable);
}
