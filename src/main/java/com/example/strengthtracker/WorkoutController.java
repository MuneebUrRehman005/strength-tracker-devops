package com.example.strengthtracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkoutController {

    @GetMapping("/workouts")
    public String getWorkouts() {
        // This tells Spring to load "workouts.html" from your templates folder
        return "workouts"; 
    }
}