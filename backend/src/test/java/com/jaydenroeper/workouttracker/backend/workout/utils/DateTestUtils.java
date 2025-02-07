package com.jaydenroeper.workouttracker.backend.workout.utils;

import java.time.LocalDate;

public class DateTestUtils {

    public static LocalDate getLocalDateOfTomorrow() {
        return LocalDate.now().plusDays(1);
    }
}
