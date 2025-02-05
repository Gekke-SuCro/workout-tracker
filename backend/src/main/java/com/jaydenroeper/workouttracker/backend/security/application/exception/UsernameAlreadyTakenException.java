package com.jaydenroeper.workouttracker.backend.security.application.exception;

public class UsernameAlreadyTakenException extends RuntimeException {

    public UsernameAlreadyTakenException(String msg) {
        super(msg);
    }

    public UsernameAlreadyTakenException() {
        this("Username is already taken.");
    }

}
