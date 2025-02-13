package com.jaydenroeper.workouttracker.backend.security.domain;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import static com.jaydenroeper.workouttracker.backend.security.config.ValidationConstants.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Roles> roles;

    protected Users() {
    }

    public Users(String username, String password, Set<Roles> roles) {
        validateUsername(username);
        validatePassword(password);
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("User roles cannot be null or empty.");
        }

        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    private void validateUsername(String username) {
        if (username == null || username.length() < USERNAME_MIN_LENGTH || username.length() > USERNAME_MAX_LENGTH) {
            throw new IllegalArgumentException(USERNAME_LENGTH_MESSAGE);
        }
        if (!username.matches(USERNAME_REGEX)) {
            throw new IllegalArgumentException(USERNAME_REGEX_MESSAGE);
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
            throw new IllegalArgumentException(PASSWORD_LENGTH_MESSAGE);
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException(PASSWORD_REGEX_MESSAGE);
        }
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }
}
