package com.student.management.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityUtilsTest {

    private final SecurityUtils securityUtils =
            new SecurityUtils();

    @AfterEach
    void cleanup() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldReturnCurrentUserEmail() {

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        "sharath@gmail.com",
                        null
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        String email =
                securityUtils.getCurrentUserEmail();

        assertEquals(
                "sharath@gmail.com",
                email
        );
    }

    @Test
    void shouldReturnAnotherUserEmail() {

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        "admin@gmail.com",
                        null
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        String email =
                securityUtils.getCurrentUserEmail();

        assertEquals(
                "admin@gmail.com",
                email
        );
    }
}