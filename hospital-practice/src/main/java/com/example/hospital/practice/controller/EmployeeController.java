package com.example.hospital.practice.controller;

import com.example.hospital.practice.dto.AppointmentDTO;
import com.example.hospital.practice.dto.EmployeeDto;
import com.example.hospital.practice.dto.PatientDto;
import com.example.hospital.practice.service.AppointmentService;
import com.example.hospital.practice.service.EmployeeService;
import com.example.hospital.practice.service.PatientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    private final Integer PAGE_SIZE=10;



    @GetMapping
    public List<EmployeeDto> getAllEmployees(
            @RequestParam(defaultValue = "0")Integer pageNo,
            @RequestParam(defaultValue = "doj")String sortBy,
            @RequestParam(defaultValue = "DESC")String direction
    ){
        Sort sort = direction.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,PAGE_SIZE,sort);

        return employeeService.getAllEmployees(pageable);
    }

    @GetMapping(path = "/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }


    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.createEmployee(employeeDto);
    }


    @PostMapping(path = "patient/{patientId}/doctor/{doctorId}")
    public AppointmentDTO createAppointment(@PathVariable Long patientId, @PathVariable Long doctorId){
        return appointmentService.createAppointment(patientId,doctorId);
    }


    @PutMapping(path = "/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(id,employeeDto);
    }

    @PatchMapping(path = "/{id}")
    public EmployeeDto partialUpdate(@PathVariable Long id, @RequestBody Map<String,Object>map){
        return employeeService.partialUpdate(id,map);
    }


    @PatchMapping(path = "/assignroom/patient/{id}")
    public Room

    //Discharge patient
    @DeleteMapping(path = "dischargePatient/{id}")
    public PatientDto dischargePatient(@PathVariable Long id){
        return patientService.dischargePatient(id);
    }
}
