package com.jaydenroeper.workouttracker.backend.workout.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.jaydenroeper.workouttracker.backend.workout.utils.DateUtils.getLocalDateOfTomorrow;
import static com.jaydenroeper.workouttracker.backend.workout.utils.TestObjectUtils.createWorkout;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private Workout workout;

    @BeforeEach
    void setUp() {
        workout = createWorkout();
    }

    @Test
    public void shouldCreateWorkout() {
        String workoutName = "Incline Chest Press";
        Workout workout = createWorkout(workoutName, LocalDate.now());

        assertNotNull(workout);
        assertEquals(workoutName, workout.getName());
    }

    @Test
    public void shouldNotCreateWorkout_whenNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(""));
    }

    @Test
    public void shouldNotCreateWorkout_whenDateIsInFuture() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(getLocalDateOfTomorrow()));
    }
}
