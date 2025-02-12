package com.jaydenroeper.workouttracker.backend.workout.domain;

import com.jaydenroeper.workouttracker.backend.security.domain.Users;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {

    private Long id;

    private Users user;
    private double weight;
    private double length;

    private List<Workout> workouts;

    public UserProfile(Users user, double weight, double length) {
        this.user = user;
        this.weight = weight;
        this.length = length;
        this.workouts = new ArrayList<>();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public double getWeight() {
        return weight;
    }

    public void addWorkout(Workout workout) {
        if (workouts.contains(workout)) {
            return;
        }

        workouts.add(workout);
        workout.setUserProfile(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("UserProfile{")
                .append("weight=").append(weight)
                .append(", length=").append(length)
                .append(", workouts=").append(workouts)
                .append("}");

        return sb.toString();
    }
}
