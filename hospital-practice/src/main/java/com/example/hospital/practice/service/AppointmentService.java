package com.example.hospital.practice.service;

import com.example.hospital.practice.dto.AppointmentDTO;
import com.example.hospital.practice.entity.Appointment;
import com.example.hospital.practice.entity.Doctor;
import com.example.hospital.practice.entity.Employee;
import com.example.hospital.practice.entity.Patient;
import com.example.hospital.practice.repository.AppointmentRepo;
import com.example.hospital.practice.repository.DoctorRepository;
import com.example.hospital.practice.repository.EmployeeRepository;
import com.example.hospital.practice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentDTO createAppointment(Long patientId, Long doctorId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new RuntimeException("Patient with"+patientId+"not found"));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->new RuntimeException("Doctor with"+doctorId+"not found"));

        Appointment appointment =
                Appointment.builder()
                        .patient(patient)
                        .doctor(doctor)
                        .localDateTime(LocalDateTime.now())
                        .build();

        return modelMapper.map(appointmentRepo.save(appointment),AppointmentDTO.class) ;
    }
}
