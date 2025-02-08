package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.application.dto.LoginResponseDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtAuthServiceTest {

    @InjectMocks
    private JwtAuthService jwtAuthService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

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
}
