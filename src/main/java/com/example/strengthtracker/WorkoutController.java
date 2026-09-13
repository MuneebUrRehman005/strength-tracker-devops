package com.example.strengthtracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutController {

    @GetMapping("/workouts")
    public String getWorkouts() {
        return "Select your workout: 1. Push Day (Chest/Triceps) 2. Pull Day (Back/Biceps) 3. Leg Day";
    }
}