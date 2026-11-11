package com.student.management.controller;

import com.student.management.dto.LoginRequest;
import com.student.management.dto.RegistrationRequest;
import com.student.management.dto.LoginResponse;
import com.student.management.dto.RegistrationResponse;
import com.student.management.service.AuthenticationService;
import com.student.management.service.RegistrationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import com.student.management.service.AuthenticationService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrationService registrationService;

    private final AuthenticationService authenticationService;


    public AuthController(
            RegistrationService registrationService,
            AuthenticationService authenticationService) {


        this.registrationService =
                registrationService;

        this.authenticationService =
                authenticationService;
    }


    // =============================================
    // REGISTER USER
    // =============================================

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request) {

        RegistrationResponse response = registrationService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    // =================================================
    // LOGIN
    // =================================================

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid
            @RequestBody
            LoginRequest request) {

        LoginResponse response =
                authenticationService.login(
                        request
                );

        return ResponseEntity.ok(
                response
        );
    }
}