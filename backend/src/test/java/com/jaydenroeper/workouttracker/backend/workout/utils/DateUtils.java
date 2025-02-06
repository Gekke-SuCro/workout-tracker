package com.jaydenroeper.workouttracker.backend.workout.utils;

import java.time.LocalDate;

public class DateUtils {

    public static LocalDate getLocalDateOfTomorrow() {
        return LocalDate.now().plusDays(1);
    }
}
