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

import static com.jaydenroeper.workouttracker.backend.security.config.AuthValidationConstants.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Roles> roles;

    protected Users() {}

    public Users(String firstName, String lastName, String username, String password, Set<Roles> roles) {
        validateName(firstName);
        validateName(lastName);
        validateUsername(username);
        validateUsername(lastName);
        validatePassword(password);
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("User roles cannot be null or empty.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    private void validateName(String name) {
        if (name == null || name.length() < NAME_MIN || name.length() > NAME_MAX) {
            throw new IllegalArgumentException("First name must be between " + NAME_MIN + " and " + NAME_MAX + " characters.");
        }
        if (!name.matches(NAME_REGEX)) {
            throw new IllegalArgumentException(NAME_MESSAGE);
        }
    }

    private void validateUsername(String username) {
        if (username == null || username.length() < USERNAME_MIN || username.length() > USERNAME_MAX) {
            throw new IllegalArgumentException("Username must be between " + USERNAME_MIN + " and " + USERNAME_MAX + " characters.");
        }
        if (!username.matches(USERNAME_REGEX)) {
            throw new IllegalArgumentException(USERNAME_MESSAGE);
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < PASSWORD_MIN || password.length() > PASSWORD_MAX) {
            throw new IllegalArgumentException("Password must be between " + PASSWORD_MIN + " and " + PASSWORD_MAX + " characters.");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException(PASSWORD_MESSAGE);
        }
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
