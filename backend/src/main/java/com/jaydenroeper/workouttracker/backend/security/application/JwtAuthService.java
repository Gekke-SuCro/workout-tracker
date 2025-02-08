package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.application.dto.LoginResponseDto;
import com.jaydenroeper.workouttracker.backend.security.application.exception.PasswordsDoNotMatchException;
import com.jaydenroeper.workouttracker.backend.security.application.exception.UsernameAlreadyTakenException;

import com.jaydenroeper.workouttracker.backend.security.data.RolesRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.mapper.UsersMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jaydenroeper.workouttracker.backend.security.data.UserRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;

import java.util.Set;

import static com.jaydenroeper.workouttracker.backend.security.config.SecurityConstants.DEFAULT_ROLE;

@Service
public class JwtAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final UsersMapper userMapper;

    public JwtAuthService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository,
            RolesRepository rolesRepository,
            UsersMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.userMapper = userMapper;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return new LoginResponseDto(loginRequestDto.username(), token);
    }

    @Override
    public Users register(RegisterRequestDto registerRequestDto) {
        if (userRepository.findByUsername(registerRequestDto.username()).isPresent()) {
            throw new UsernameAlreadyTakenException();
        }
        if (!(registerRequestDto.password().equals(registerRequestDto.confirmPassword()))) {
            throw new PasswordsDoNotMatchException();
        }

        Roles userRole = rolesRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new RuntimeException("User role not found!"));
        Users newUser = userMapper.toUser(registerRequestDto, Set.of(userRole));

        return userRepository.save(newUser);
    }
}
