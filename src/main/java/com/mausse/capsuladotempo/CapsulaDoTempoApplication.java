package com.mausse.capsuladotempo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CapsulaDoTempoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapsulaDoTempoApplication.class, args);
    }

}
