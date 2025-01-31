package com.jaydenroeper.workouttracker.backend.security.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
