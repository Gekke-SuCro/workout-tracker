package com.jaydenroeper.workouttracker.backend.workout.domain;

public class Exercise {

    private Long id;
    private String name;
    private ExerciseValueType type;

    public Exercise(String name, ExerciseValueType type) {
        this.name = name;
        this.type = type;
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
