package com.student.management.service;

import com.student.management.dto.RegistrationRequest;
import com.student.management.dto.RegistrationResponse;
import com.student.management.entity.User;
import com.student.management.enums.Role;
import com.student.management.exception.UserAlreadyExistsException;
import com.student.management.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void shouldRegisterUserSuccessfully() {

        RegistrationRequest request =
                new RegistrationRequest();

        request.setEmail("sharath@gmail.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail(
                "sharath@gmail.com"))
                .thenReturn(false);

        when(passwordEncoder.encode(
                "password123"))
                .thenReturn("encodedPassword");

        User savedUser = new User();

        savedUser.setEmail("sharath@gmail.com");
        savedUser.setRole(Role.STUDENT);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        RegistrationResponse response =
                registrationService.register(request);

        assertNotNull(response);

        assertEquals(
                "sharath@gmail.com",
                response.getEmail()
        );

        assertEquals(
                Role.STUDENT,
                response.getRole()
        );

        verify(userRepository,
                times(1))
                .save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        RegistrationRequest request =
                new RegistrationRequest();

        request.setEmail("sharath@gmail.com");

        when(userRepository.existsByEmail(
                "sharath@gmail.com"))
                .thenReturn(true);

        assertThrows(
                UserAlreadyExistsException.class,
                () -> registrationService.register(request)
        );

        verify(passwordEncoder,
                never())
        .encode(anyString());

        verify(userRepository,
                never())
                .save(any(User.class));
    }
}