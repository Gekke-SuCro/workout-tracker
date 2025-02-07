package com.jaydenroeper.workouttracker.backend.workout.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.jaydenroeper.workouttracker.backend.workout.utils.DateTestUtils.getLocalDateOfTomorrow;
import static com.jaydenroeper.workouttracker.backend.workout.utils.WorkoutTestFactory.createWorkout;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private final String workoutNameExceedingMaxLength = "Upper, Middle and Lower Chest Workout Exercises Day"; // 51 char name

    @Test
    public void Should_CreateWorkout_When_ValidArguments() {
        String workoutName = "Incline Chest Press";
        LocalDate today = LocalDate.now();
        Workout workout = createWorkout(workoutName, today);

        assertNotNull(workout);
        assertEquals(workoutName, workout.getName());
        assertEquals(today, workout.getDate());
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(""));
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutNameExceeds50Chars() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(workoutNameExceedingMaxLength));
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutNameContainsSpecialCharachters() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout("@W0rk04t"));
   }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutNameStartsWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(" Workout"));
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutNameContainsDoubleWhitespaces() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout("Workout  Day"));
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutNameEndsWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout("Workout "));
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutDateIsInFuture() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(getLocalDateOfTomorrow()));
    }
}
