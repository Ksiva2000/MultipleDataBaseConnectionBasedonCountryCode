package com.tanasvi.multidb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.tanasvi.multidb.entity")
public class MultidbApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(MultidbApplication.class, args);
    }
}
