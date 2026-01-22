package com.example.hospital.practice.service;

import com.example.hospital.practice.dto.AppointmentDTO;
import com.example.hospital.practice.entity.Appointment;
import com.example.hospital.practice.repository.AppointmentRepo;
import com.example.hospital.practice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final AppointmentRepo appointmentRepo;

    public List<AppointmentDTO> getAllAppointment(Pageable pageable,Long id){
        Page<Appointment> appointmentPage = appointmentRepo.findByDoctorId(id,pageable);

        return appointmentPage
                .stream()
                .map((element) -> modelMapper.map(element, AppointmentDTO.class))
                .toList();
    }

}
