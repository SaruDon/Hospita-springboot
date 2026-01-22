package com.example.hospital.practice.service;

import com.example.hospital.practice.dto.PatientDto;
import com.example.hospital.practice.entity.Patient;
import com.example.hospital.practice.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;


    public List<PatientDto> getAllPatient(Pageable pageable){
        List<Patient> patients= patientRepository.findAll(pageable).getContent();
        return patients
                .stream()
                .map((element) -> modelMapper.map(element, PatientDto.class))
                .toList();
    }

    public PatientDto admitPatient(PatientDto patientDto){
         Patient patient = modelMapper.map(patientDto, Patient.class);
         return modelMapper.map(patientRepository.save(patient), PatientDto.class);
    }

    public PatientDto updatePatientInfo(PatientDto dto, Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found " + id));

        if(dto.getName() != null) patient.setName(dto.getName());
        if(dto.getAge() != null) patient.setAge(dto.getAge());
        if(dto.getDob() != null) patient.setDob(dto.getDob());
        if(dto.getPhoneNo() != null) patient.setPhoneNo(dto.getPhoneNo());
        if(dto.getGender() != null) patient.setGender(dto.getGender());

        return modelMapper.map(patientRepository.save(patient), PatientDto.class);
    }


    public PatientDto updatePartial(Long id, Map<String, Object> updates){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Resource not found "+ id));
        updates.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Patient.class,key);

            if (field == null) {
                throw new RuntimeException("Invalid field: " + key);
            }

            field.setAccessible(true);
            ReflectionUtils.setField(field,patient,value);
        });

        return modelMapper.map(patientRepository.save(patient),PatientDto.class);
    }

    @Transactional
    public PatientDto dischargePatient(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("Patient with id"+id+"not found"));
        patientRepository.delete(patient);
        return modelMapper.map(patient, PatientDto.class);
    }


}
