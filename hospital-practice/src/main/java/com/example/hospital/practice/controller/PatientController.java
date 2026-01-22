package com.example.hospital.practice.controller;

import com.example.hospital.practice.dto.PatientDto;
import com.example.hospital.practice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path = "patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    private final Integer PAGE_SIZE=10;

    @GetMapping
    public List<PatientDto> getAllPatient(
            @RequestParam(defaultValue = "0")Integer pageNo,
            @RequestParam(defaultValue = "admittedDate") String sortBy,
            @RequestParam(defaultValue = "DESC")String direction
    ){
        Sort sort = direction.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() :  Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,PAGE_SIZE,sort);
        return patientService.getAllPatient(pageable);
    }

    @PostMapping
    public PatientDto admitPatient(@RequestBody PatientDto patientDto){
        return patientService.admitPatient(patientDto);
    }

    @PutMapping(path = "/{id}")
    public PatientDto updatePatient(@RequestBody PatientDto patientDto,@PathVariable Long id){
        return patientService.updatePatientInfo(patientDto,id);
    }

    @PatchMapping(path = "/{id}")
    public PatientDto updatePartial(@PathVariable Long id , @RequestBody Map<String,Object>updates){
        return patientService.updatePartial(id,updates);
    }
}
