package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.application.dto.LoginResponseDto;
import com.jaydenroeper.workouttracker.backend.security.application.exception.PasswordsDoNotMatchException;
import com.jaydenroeper.workouttracker.backend.security.application.exception.UsernameAlreadyTakenException;
import com.jaydenroeper.workouttracker.backend.security.data.RolesRepository;
import com.jaydenroeper.workouttracker.backend.security.data.UserRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.mapper.UsersMapper;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.Set;

import static com.jaydenroeper.workouttracker.backend.security.utils.RolesTestFactory.createRole;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtAuthServiceTest {

    @InjectMocks
    private JwtAuthService jwtAuthService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RolesRepository rolesRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UsersMapper usersMapper;

    @Test
    void should_Login() {
        String dummyUsername = "user";
        String dummyToken = "dummyToken";
        LoginRequestDto loginRequestDto = new LoginRequestDto("user", "1234");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(dummyToken);

        LoginResponseDto loginResponseDto = jwtAuthService.login(loginRequestDto);

        assertEquals(dummyUsername, loginResponseDto.username());
        assertEquals(dummyToken, loginResponseDto.token());
    }

    @Test
    void Should_ThrowException_When_InvalidCredentials() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("user", "password");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> jwtAuthService.login(loginRequestDto));
    }

    @Test
    public void Should_Register() {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("user", "password", "password");
        when(userRepository.findByUsername(registerRequestDto.username())).thenReturn(Optional.empty());
        Roles userRole = createRole();
        when(rolesRepository.findByName(userRole.getName())).thenReturn(Optional.of(userRole));
        Users newUser = mock(Users.class);
        when(usersMapper.toUser(registerRequestDto, Set.of(userRole))).thenReturn(newUser);
        when(userRepository.save(newUser)).thenReturn(newUser);

        Users registeredUser = jwtAuthService.register(registerRequestDto);

        assertNotNull(registeredUser);
    }

    @Test
    public void Should_ThrowException_When_UsernameAlreadyTaken() {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("user", "password", "password");
        when(userRepository.findByUsername(registerRequestDto.username())).thenReturn(Optional.of(mock(Users.class)));

        assertThrows(UsernameAlreadyTakenException.class, () -> jwtAuthService.register(registerRequestDto));
    }

    @Test
    public void testRegister_PasswordsDoNotMatch() {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("user", "password123", "password456");
        assertThrows(PasswordsDoNotMatchException.class, () -> jwtAuthService.register(registerRequestDto));
    }
}
