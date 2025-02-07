package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.application.exception.PasswordsDoNotMatchException;
import com.jaydenroeper.workouttracker.backend.security.application.exception.UsernameAlreadyTakenException;
import com.jaydenroeper.workouttracker.backend.security.data.RolesRepository;
import com.jaydenroeper.workouttracker.backend.security.data.UserRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static com.jaydenroeper.workouttracker.backend.utils.TestObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class JwtAuthServiceTest {

    @InjectMocks
    private JwtAuthService jwtAuthService;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RolesRepository rolesRepository;

    private String dummyToken;
    private LoginRequestDto loginRequestDto;
    private RegisterRequestDto registerRequestDto;

    @BeforeEach
    void setup() {
        dummyToken = "dummyToken";
        loginRequestDto = createLoginRequestDto();
        registerRequestDto = createRegisterRequestDto();
    }

    @Test
    public void verify_shouldReturnNull_whenNotValidCredentials() {
        assertNull(jwtAuthService.verify(loginRequestDto));
    }

    @Test
    public void verify_shouldReturnToken_whenValidCredentials() {
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(dummyToken);
        String token = jwtAuthService.verify(loginRequestDto);

        assertEquals(dummyToken, token);
    }

    @Test
    public void register_shouldThrowUsernameAlreadyExistException_whenUsernameExists() {
        when(userRepository.findByUsername(registerRequestDto.getUsername()))
                .thenReturn(Optional.of(mock(Users.class)));

        assertThrows(UsernameAlreadyTakenException.class, () -> jwtAuthService.register(registerRequestDto));
    }

    @Test
    public void register_shouldThrowPasswordsDoNotMatchException_whenPasswordsDoNotMatch() {
        registerRequestDto.setConfirmPassword("notMatchingPassword");
        when(userRepository.findByUsername(registerRequestDto.getUsername()))
                .thenReturn(Optional.empty());

        assertThrows(PasswordsDoNotMatchException.class, () -> jwtAuthService.register(registerRequestDto));
    }

    @Test
    public void register_shouldSaveUser_whenRegistrationIsValid() {
        when(rolesRepository.findByName("ROLE_USER"))
                .thenReturn(Optional.of(getUserRole()));
        when(userRepository.findByUsername(registerRequestDto.getUsername()))
                .thenReturn(Optional.empty());
        when(userRepository.save(any(Users.class))).thenReturn(createUser());
        Users result = jwtAuthService.register(registerRequestDto);

        assertNotNull(result);
        verify(userRepository).save(any(Users.class));
    }
}
