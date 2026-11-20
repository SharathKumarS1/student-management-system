package com.student.management.security;

import com.student.management.entity.User;
import com.student.management.enums.Role;
import com.student.management.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void shouldLoadUserByUsername() {

        User user = new User();

        user.setEmail("sharath@gmail.com");
        user.setPassword("password123");
        user.setRole(Role.STUDENT);

        when(userRepository.findByEmail("sharath@gmail.com"))
                .thenReturn(Optional.of(user));

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(
                        "sharath@gmail.com"
                );

        assertNotNull(userDetails);

        assertEquals(
                "sharath@gmail.com",
                userDetails.getUsername()
        );

        assertEquals(
                "password123",
                userDetails.getPassword()
        );
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(userRepository.findByEmail("unknown@gmail.com"))
                .thenReturn(Optional.empty());

        assertThrows(
                UsernameNotFoundException.class,
                () -> customUserDetailsService
                        .loadUserByUsername(
                                "unknown@gmail.com"
                        )
        );
    }
}