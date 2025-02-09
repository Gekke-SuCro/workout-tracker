package com.jaydenroeper.workouttracker.backend.workout.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.jaydenroeper.workouttracker.backend.workout.config.ValidationConstants.*;
import static com.jaydenroeper.workouttracker.backend.workout.utils.DateTestUtils.getLocalDateOfTomorrow;
import static com.jaydenroeper.workouttracker.backend.workout.utils.WorkoutTestFactory.*;
import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private static Stream<String> provideInvalidWorkoutNames() {
        return Stream.of(
                null,
                "",
                "A".repeat(WORKOUT_NAME_MIN_LENGTH - 1),
                "A".repeat(WORKOUT_NAME_MAX_LENGTH + 1),
                " " + "A".repeat(WORKOUT_NAME_MIN_LENGTH),
                "A".repeat(WORKOUT_NAME_MIN_LENGTH) + " "
        );
    }

    @Test
    public void Should_CreateWorkout_When_ValidArguments() {
        Workout workout = createWorkout(DUMMY_WORKOUT_NAME, DUMMY_WORKOUT_DATE);

        assertNotNull(workout);
        assertEquals(DUMMY_WORKOUT_NAME, workout.getName());
        assertEquals(DUMMY_WORKOUT_DATE, workout.getDate());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidWorkoutNames")
    void Should_ThrowException_When_WorkoutNameIsInvalid(String name) {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(name, DUMMY_WORKOUT_DATE));
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(DUMMY_WORKOUT_NAME, null));
    }

    @Test
    public void Should_ThrowIllegalArgumentException_When_WorkoutDateIsInFuture() {
        assertThrows(IllegalArgumentException.class, () -> createWorkout(DUMMY_WORKOUT_NAME, getLocalDateOfTomorrow()));
    }
}
