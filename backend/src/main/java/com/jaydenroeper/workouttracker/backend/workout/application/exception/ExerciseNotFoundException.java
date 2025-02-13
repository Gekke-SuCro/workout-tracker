package com.jaydenroeper.workouttracker.backend.workout.application.exception;

public class ExerciseNotFoundException extends RuntimeException {

    public ExerciseNotFoundException(String msg) {
        super(msg);
    }

    public ExerciseNotFoundException() {
        this("Exercise Not Found");
    }
}
