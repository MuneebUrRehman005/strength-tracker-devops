package com.example.strengthtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StrengthTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrengthTrackerApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Strength Tracker Web Application!";
    }
}