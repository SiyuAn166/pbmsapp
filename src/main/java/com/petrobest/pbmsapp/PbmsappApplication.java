package com.petrobest.pbmsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class PbmsappApplication {

    public static void main(String[] args) {
        SpringApplication.run(PbmsappApplication.class, args);
    }

}
