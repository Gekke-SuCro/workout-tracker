package com.jaydenroeper.workouttracker.backend.workout.domain;

public class Exercise {

    private Long id;
    private String name;

    public Exercise(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Exercise{")
                .append("name=").append(name)
                .append("}");

        return sb.toString();
    }
}
