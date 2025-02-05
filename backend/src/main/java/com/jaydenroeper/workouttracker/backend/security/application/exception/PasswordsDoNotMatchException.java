package com.jaydenroeper.workouttracker.backend.security.application.exception;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException(String msg) {
        super(msg);
    }

    public PasswordsDoNotMatchException() {
        this("Passwords do not match.");
    }

}
