package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.data.RolesRepository;
import com.jaydenroeper.workouttracker.backend.security.data.UserRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.jaydenroeper.workouttracker.backend.utils.TestObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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

    private LoginRequestDto loginRequestDto;
    private RegisterRequestDto registerRequestDto;

    @BeforeEach
    void setUp() {
        loginRequestDto = createLoginRequestDto();
        registerRequestDto = createRegisterRequestDto();
    }

    @Test
    public void verify_shouldThrowBadCredentialsException_whenNotValidCredentials() {
        assertThrows(BadCredentialsException.class, () -> jwtAuthService.verify(loginRequestDto));
    }

    @Test
    public void verify_shouldReturnToken_whenValidCredentials() {
        userRepository.save(createUser());
        assertNotNull(jwtAuthService.verify(loginRequestDto));
    }

    @Test
    public void register_shouldSaveUser_whenRegistrationIsValid() {
        Users result = jwtAuthService.register(registerRequestDto);
        assertNotNull(result);

        Optional<Users> savedUser = userRepository.findByUsername(registerRequestDto.getUsername());
        assertTrue(savedUser.isPresent());
        assertEquals(registerRequestDto.getUsername(), savedUser.get().getUsername());
    }
}
