package com.jaydenroeper.workouttracker.backend.security.application;


import com.jaydenroeper.workouttracker.backend.security.data.RolesRepository;
import com.jaydenroeper.workouttracker.backend.security.data.UserRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jaydenroeper.workouttracker.backend.utils.TestObjectUtils.createRegisterRequestDto;
import static com.jaydenroeper.workouttracker.backend.utils.TestObjectUtils.createUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class JwtAuthServiceIntegrationTest {

    @Autowired
    private JwtAuthService jwtAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void verify_shouldReturnToken_whenValidCredentials() {
        String rawPassword = "password";
        Users user = createUser();
        user.setPassword(encoder.encode(rawPassword));
        userRepository.save(user);

        LoginRequestDto loginRequestDto = new LoginRequestDto(user.getUsername(), rawPassword);
        String token = jwtAuthService.verify(loginRequestDto);

        assertNotNull(token);
    }

    @Test
    public void register_shouldSaveUser_whenRegistrationIsValid() {
        RegisterRequestDto registerRequestDto = createRegisterRequestDto();
        Users result = jwtAuthService.register(registerRequestDto);

        assertNotNull(result);
    }
}
