package com.jaydenroeper.workouttracker.backend.workout.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Workout {

    private Long id;
    private String name;
    private LocalDate date;

    public Workout(String name, LocalDate date) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (date == null || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Workout date cannot be in the future.");
        }

        this.name = name;
        this.date = date;
    }
}
