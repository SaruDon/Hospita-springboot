package com.example.hospital.practice.service;

import com.example.hospital.practice.dto.EmployeeDto;
import com.example.hospital.practice.entity.Employee;
import com.example.hospital.practice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployees(Pageable pageable){
        List<Employee> employeeList = employeeRepository.findAll(pageable).getContent();
        return employeeList.stream().map((element) -> modelMapper.map(element, EmployeeDto.class)).collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee with id"+id+"not found"));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        employee.setId(id);
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    public EmployeeDto partialUpdate(Long id, Map<String,Object> updates){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found"));

        updates.forEach((key, value)->{
            Field field = ReflectionUtils.findField(Employee.class,key);

            if (field==null){
                throw new RuntimeException("Invalid field: " + key);
            }

            field.setAccessible(true);
            ReflectionUtils.setField(field,employee,value);
        });


        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

}
