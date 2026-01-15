package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class TravelManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelManagementApplication.class, args);
        System.out.println("Travel Management System Started Successfully!");
        System.out.println("Swagger UI: http://localhost:8080/api/swagger-ui.html");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
