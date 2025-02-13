package com.jaydenroeper.workouttracker.backend.workout.application.exception;

public class NoSetsFoundException extends RuntimeException {

    public NoSetsFoundException(String msg) {
        super(msg);
    }

    public NoSetsFoundException() {
        this("No sets found");
    }
}
