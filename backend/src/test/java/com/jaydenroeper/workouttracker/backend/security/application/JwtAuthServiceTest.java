package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.application.exception.PasswordsDoNotMatchException;
import com.jaydenroeper.workouttracker.backend.security.application.exception.UsernameAlreadyTakenException;
import com.jaydenroeper.workouttracker.backend.security.data.RolesRepository;
import com.jaydenroeper.workouttracker.backend.security.data.UserRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static com.jaydenroeper.workouttracker.backend.utils.TestObjectUtils.createRegisterRequestDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
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

    @Test
    public void verify_shouldReturnToken_whenValidCredentials() {
        String dummyToken = "'dummyToken'";
        LoginRequestDto loginRequestDto = new LoginRequestDto("user", "password");
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(dummyToken);

        String token = jwtAuthService.verify(loginRequestDto);

        assertEquals(dummyToken, token);
    }

    @Test
    public void register_shouldThrowUsernameAlreadyExistException_whenUsernameExists() {
        RegisterRequestDto registerRequestDto = createRegisterRequestDto();
        when(userRepository.findByUsername(registerRequestDto.getUsername())).thenReturn(Optional.of(mock(Users.class)));

        assertThrows(UsernameAlreadyTakenException.class, () -> {
            jwtAuthService.register(registerRequestDto);
        });
    }

    @Test
    public void register_shouldThrowPasswordsDoNotMatchException_whenPasswordsDoNotMatch() {
        RegisterRequestDto registerRequestDto = createRegisterRequestDto();
        registerRequestDto.setConfirmPassword("notMatchingPassword");
        when(userRepository.findByUsername(registerRequestDto.getUsername())).thenReturn(Optional.empty());

        assertThrows(PasswordsDoNotMatchException.class, () -> {
            jwtAuthService.register(registerRequestDto);
        });
    }

    @Test
    public void register_shouldSaveUser_whenRegistrationIsValid() {
        RegisterRequestDto registerRequestDto = createRegisterRequestDto();
        Users newUser = new Users();

        when(rolesRepository.findByName("ROLE_USER")).thenReturn(Optional.ofNullable(mock(Roles.class)));
        when(userRepository.findByUsername(registerRequestDto.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(any(Users.class))).thenReturn(newUser);

        Users result = jwtAuthService.register(registerRequestDto);

        assertNotNull(result);
    }
}
