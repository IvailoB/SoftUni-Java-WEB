package com.example.shippinglist.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfigurations {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
