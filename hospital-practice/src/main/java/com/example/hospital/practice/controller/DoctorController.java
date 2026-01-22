package com.example.hospital.practice.controller;

import com.example.hospital.practice.dto.AppointmentDTO;
import com.example.hospital.practice.entity.Doctor;
import com.example.hospital.practice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final Integer PAGE_SIZE=10;
    private final DoctorService doctorService;

    @GetMapping(path = "appointment/{id}")
    public List<AppointmentDTO> getAllAppointment(
            @PathVariable Long id,
            @RequestParam(defaultValue = "DESC")String direction,
            @RequestParam(defaultValue = "localDateTime")String localDateTime,
            @RequestParam(defaultValue = "0")Integer pageNo
    ){
        Sort sortBy = direction.equalsIgnoreCase("ASC") ? Sort.by(localDateTime).ascending() : Sort.by(localDateTime).descending();

        Pageable pageable = PageRequest.of(pageNo,PAGE_SIZE,sortBy);

        return doctorService.getAllAppointment(pageable,id);
    }

}
