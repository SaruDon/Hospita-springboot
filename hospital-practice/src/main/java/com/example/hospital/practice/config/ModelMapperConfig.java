package com.example.hospital.practice.config;

import com.example.hospital.practice.dto.PatientDto;
import com.example.hospital.practice.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();

        return mapper;
    }
}
