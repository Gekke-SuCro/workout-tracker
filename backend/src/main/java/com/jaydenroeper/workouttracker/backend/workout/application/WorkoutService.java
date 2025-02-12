package com.jaydenroeper.workouttracker.backend.workout.application;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.domain.UserProfile;
import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutService {

    private final Map<String, UserProfile> userProfiles = new HashMap<>();
    private final List<Workout> workouts = new ArrayList<>();


    public Workout createWorkout(WorkoutRequestDto workoutRequestDto) {
        return null;
    }
}
