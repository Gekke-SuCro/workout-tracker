package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.application.exception.PasswordsDoNotMatchException;
import com.jaydenroeper.workouttracker.backend.security.application.exception.UsernameAlreadyTakenException;

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

@Service
public class JwtAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder ;

    public JwtAuthService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder(12);
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

        Users newUser = Users.builder()
                .firstname(registerRequestDto.getFirstname())
                .lastname(registerRequestDto.getLastname())
                .username(registerRequestDto.getUsername())
                .password(encoder.encode(registerRequestDto.getPassword()))
                .build();

        return userRepository.save(newUser);
    }
}
