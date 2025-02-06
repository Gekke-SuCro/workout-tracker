package com.jaydenroeper.workouttracker.backend.workout.utils;

import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class TestObjectUtils {

    private static final String dummyWorkoutName = "Workout";
    private static final LocalDate dummyWorkoutDate = LocalDate.now();

    public static Workout createWorkout(String name, LocalDate date) {
        return new Workout(name, date);
    }

    public static Workout createWorkout(String name) {
        return createWorkout(name, dummyWorkoutDate);
    }

    public static Workout createWorkout(LocalDate date) {
        return createWorkout(dummyWorkoutName, date);
    }

    public static Workout createWorkout() {
        return createWorkout(dummyWorkoutName, dummyWorkoutDate);
    }
}
