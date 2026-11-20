package com.student.management.service;

import com.student.management.dto.LoginRequest;
import com.student.management.dto.LoginResponse;
import com.student.management.enums.Role;
import com.student.management.repository.UserRepository;
import com.student.management.security.JwtService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest request = new LoginRequest();
        request.setEmail("sharath@gmail.com");
        request.setPassword("password123");

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("sharath@gmail.com")
                .password("password123")
                .authorities("ROLE_STUDENT")
                .build();

        com.student.management.entity.User user =
                new com.student.management.entity.User();

        user.setEmail("sharath@gmail.com");
        user.setRole(Role.STUDENT);

        when(userDetailsService.loadUserByUsername(
                "sharath@gmail.com"))
                .thenReturn(userDetails);

        when(jwtService.generateToken(userDetails))
                .thenReturn("jwt-token");

        when(userRepository.findByEmail(
                "sharath@gmail.com"))
                .thenReturn(Optional.of(user));

        LoginResponse response =
                authenticationService.login(request);

        assertNotNull(response);

        assertEquals(
                "jwt-token",
                response.getToken()
        );

        assertEquals(
                "sharath@gmail.com",
                response.getEmail()
        );

        assertEquals(
                Role.STUDENT,
                response.getRole()
        );

        verify(authenticationManager)
                .authenticate(any(
                        UsernamePasswordAuthenticationToken.class
                ));
    }
}
