package com.jaydenroeper.workouttracker.backend.security.data.exception;

public class UsernameNotUniqueException extends RuntimeException {

    public UsernameNotUniqueException(String msg) {
        super(msg);
    }

}
