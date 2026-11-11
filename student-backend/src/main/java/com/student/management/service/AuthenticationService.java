package com.student.management.service;

import com.student.management.dto.LoginRequest;
import com.student.management.dto.LoginResponse;
import com.student.management.entity.User;
import com.student.management.repository.UserRepository;
import com.student.management.security.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final AuthenticationManager
            authenticationManager;

    private final UserDetailsService
            userDetailsService;

    private final JwtService
            jwtService;

    private final UserRepository
            userRepository;


    public AuthenticationService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService,
            UserRepository userRepository) {

        this.authenticationManager =
                authenticationManager;

        this.userDetailsService =
                userDetailsService;

        this.jwtService =
                jwtService;

        this.userRepository =
                userRepository;
    }


    public LoginResponse login(
            LoginRequest request) {

        // =============================================
        // AUTHENTICATE USER
        // =============================================

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        // =============================================
        // LOAD USER DETAILS
        // =============================================

        UserDetails userDetails =
                userDetailsService
                        .loadUserByUsername(
                                request.getEmail()
                        );


        // =============================================
        // GENERATE JWT
        // =============================================

        String token =
                jwtService.generateToken(
                        userDetails
                );


        // =============================================
        // LOAD USER ENTITY
        // =============================================

        User user =
                userRepository
                        .findByEmail(
                                request.getEmail()
                        )
                        .orElseThrow();


        // =============================================
        // RESPONSE
        // =============================================

        return new LoginResponse(
                token,
                "Bearer",
                user.getEmail(),
                user.getRole()
        );
    }
}