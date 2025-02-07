package com.jaydenroeper.workouttracker.backend.workout.domain;

import java.time.LocalDate;

import static com.jaydenroeper.workouttracker.backend.workout.domain.Validator.validateWorkout;

public class Workout {

    private Long id;
    private String name;
    private LocalDate date;

    public Workout(String name, LocalDate date) {
        validateWorkout(name, date);
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}
