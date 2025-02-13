package com.jaydenroeper.workouttracker.backend.workout.presentation.controller;

import com.jaydenroeper.workouttracker.backend.workout.application.WorkoutService;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> loadAllWorkouts() {
        try {
            return ResponseEntity.ok(workoutService.findAllWorkouts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutRequestDto workoutRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(workoutService.createWorkout(workoutRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
