package com.example.ds2022_30241_fariseu_teodora.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@EnableSpringConfigured
public class WebConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}