package com.student.management.security;

import com.student.management.config.JwtProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @Mock
    private JwtProperties jwtProperties;

    private JwtService jwtService;

    @BeforeEach
    void setup() {

        when(jwtProperties.getSecret())
                .thenReturn(
                        "mySecretKeyForJwtTokenGeneration12345678901234567890"
                );

        when(jwtProperties.getExpiration())
                .thenReturn(86400000L);

        jwtService =
                new JwtService(jwtProperties);
    }

    @Test
    void shouldGenerateToken() {

        UserDetails userDetails =
                User.withUsername("sharath@gmail.com")
                        .password("password")
                        .authorities(Collections.emptyList())
                        .build();

        String token =
                jwtService.generateToken(userDetails);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldExtractUsername() {

        UserDetails userDetails =
                User.withUsername("sharath@gmail.com")
                        .password("password")
                        .authorities(Collections.emptyList())
                        .build();

        String token =
                jwtService.generateToken(userDetails);

        String username =
                jwtService.extractUsername(token);

        assertEquals(
                "sharath@gmail.com",
                username
        );
    }

    @Test
    void shouldValidateToken() {

        UserDetails userDetails =
                User.withUsername("sharath@gmail.com")
                        .password("password")
                        .authorities(Collections.emptyList())
                        .build();

        String token =
                jwtService.generateToken(userDetails);

        assertTrue(
                jwtService.isTokenValid(
                        token,
                        userDetails
                )
        );
    }

    @Test
    void shouldReturnFalseForInvalidUser() {

        UserDetails originalUser =
                User.withUsername("sharath@gmail.com")
                        .password("password")
                        .authorities(Collections.emptyList())
                        .build();

        String token =
                jwtService.generateToken(originalUser);

        UserDetails anotherUser =
                User.withUsername("other@gmail.com")
                        .password("password")
                        .authorities(Collections.emptyList())
                        .build();

        assertFalse(
                jwtService.isTokenValid(
                        token,
                        anotherUser
                )
        );
    }

    @Test
    void shouldExtractExpirationDate() {

        UserDetails userDetails =
                User.withUsername("sharath@gmail.com")
                        .password("password")
                        .authorities(Collections.emptyList())
                        .build();

        String token =
                jwtService.generateToken(userDetails);

        assertNotNull(
                jwtService.extractExpiration(token)
        );
    }
}