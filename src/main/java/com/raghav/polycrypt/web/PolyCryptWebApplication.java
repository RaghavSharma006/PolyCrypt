package com.raghav.polycrypt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.raghav.polycrypt")
public class PolyCryptWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolyCryptWebApplication.class, args);
        System.out.println("Hello World!");
    }

}