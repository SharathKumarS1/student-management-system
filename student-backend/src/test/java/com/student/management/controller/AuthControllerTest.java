package com.student.management.controller;

import com.student.management.dto.LoginRequest;
import com.student.management.dto.LoginResponse;
import com.student.management.dto.RegistrationRequest;
import com.student.management.dto.RegistrationResponse;
import com.student.management.enums.Role;
import com.student.management.service.AuthenticationService;
import com.student.management.service.RegistrationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private RegistrationService registrationService;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    @Test
    void shouldRegisterUser() {

        RegistrationRequest request =
                new RegistrationRequest();

        request.setEmail("sharath@gmail.com");
        request.setPassword("password123");

        RegistrationResponse response =
                mock(RegistrationResponse.class);

        when(registrationService.register(request))
                .thenReturn(response);

        ResponseEntity<RegistrationResponse> result =
                authController.register(request);

        assertEquals(
                HttpStatus.CREATED,
                result.getStatusCode()
        );

        assertEquals(
                response,
                result.getBody()
        );
    }

    @Test
    void shouldLoginUser() {

        LoginRequest request =
                new LoginRequest();

        request.setEmail("sharath@gmail.com");
        request.setPassword("password123");

        LoginResponse response =
                new LoginResponse(
                        "jwt-token",
                        "Bearer",
                        "sharath@gmail.com",
                        Role.STUDENT
                );

        when(authenticationService.login(request))
                .thenReturn(response);

        ResponseEntity<LoginResponse> result =
                authController.login(request);

        assertEquals(
                HttpStatus.OK,
                result.getStatusCode()
        );

        assertEquals(
                response,
                result.getBody()
        );
    }
}