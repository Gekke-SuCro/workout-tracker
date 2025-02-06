package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.application.exception.PasswordsDoNotMatchException;
import com.jaydenroeper.workouttracker.backend.security.application.exception.UsernameAlreadyTakenException;

import com.jaydenroeper.workouttracker.backend.security.data.RolesRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jaydenroeper.workouttracker.backend.security.data.UserRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;

import java.util.Collections;
import java.util.Optional;

@Service
public class JwtAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder encoder ;

    public JwtAuthService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository, RolesRepository rolesRepository,
            BCryptPasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.encoder = encoder;
    }

    @Override
    public String verify(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public Users register(RegisterRequestDto registerRequestDto) {
        if (userRepository.findByUsername(registerRequestDto.getUsername()).isPresent()) {
            throw new UsernameAlreadyTakenException();
        }

        if (!(registerRequestDto.getPassword().equals(registerRequestDto.getConfirmPassword()))) {
            throw new PasswordsDoNotMatchException();
        }

        Optional<Roles> userRole = rolesRepository.findByName("ROLE_USER");
        if (userRole.isEmpty()) {
            throw new RuntimeException("User role not found!");
        }

        Users newUser = Users.builder()
                .firstname(registerRequestDto.getFirstname())
                .lastname(registerRequestDto.getLastname())
                .username(registerRequestDto.getUsername())
                .password(encoder.encode(registerRequestDto.getPassword()))
                .roles(Collections.singleton(userRole.get()))
                .build();

        return userRepository.save(newUser);
    }
}
