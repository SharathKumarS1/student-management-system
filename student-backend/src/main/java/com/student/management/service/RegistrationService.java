package com.student.management.service;

import com.student.management.dto.RegistrationRequest;
import com.student.management.dto.RegistrationResponse;
import com.student.management.entity.User;
import com.student.management.enums.Role;
import com.student.management.exception.UserAlreadyExistsException;
import com.student.management.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public RegistrationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository =
                userRepository;

        this.passwordEncoder =
                passwordEncoder;
    }


    @Transactional
    public RegistrationResponse register(
            RegistrationRequest request) {

        // =============================================
        // 1. CHECK DUPLICATE EMAIL
        // =============================================

        if (userRepository
                .existsByEmail(
                        request.getEmail()
                )) {

            throw new UserAlreadyExistsException(
                    "User already exists with email: "
                            + request.getEmail()
            );
        }


        // =============================================
        // 2. HASH PASSWORD
        // =============================================

        String encodedPassword =
                passwordEncoder.encode(
                        request.getPassword()
                );


        // =============================================
        // 3. CREATE USER
        // =============================================

        User user =
                new User();

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                encodedPassword
        );

        user.setRole(
                Role.STUDENT
        );


        // =============================================
        // 4. SAVE USER
        // =============================================

        User savedUser =
                userRepository.save(
                        user
                );


        // =============================================
        // 5. RETURN SAFE RESPONSE
        // =============================================

        return new RegistrationResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCreatedAt()
        );
    }
}