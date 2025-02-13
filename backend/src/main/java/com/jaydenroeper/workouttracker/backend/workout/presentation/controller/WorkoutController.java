package com.jaydenroeper.workouttracker.backend.workout.presentation.controller;

import com.jaydenroeper.workouttracker.backend.workout.application.WorkoutService;
import com.jaydenroeper.workouttracker.backend.workout.application.exception.ExerciseNotFoundException;
import com.jaydenroeper.workouttracker.backend.workout.domain.UserProfile;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<?> loadWorkoutsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(workoutService.findAllWorkoutsByUsername(userDetails.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createWorkout(@AuthenticationPrincipal UserDetails userDetails, @RequestBody WorkoutRequestDto workoutRequestDto) {
        try {
            workoutService.createWorkout(userDetails.getUsername(), workoutRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workout successfully created");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        catch (ExerciseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
