package com.student.management.dto;

import com.student.management.enums.Role;

import java.time.LocalDateTime;

public class RegistrationResponse {

    private Long userId;

    private String email;

    private Role role;

    private LocalDateTime createdAt;


    public RegistrationResponse(
            Long userId,
            String email,
            Role role,
            LocalDateTime createdAt) {

        this.userId = userId;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }


    public Long getUserId() {
        return userId;
    }


    public String getEmail() {
        return email;
    }


    public Role getRole() {
        return role;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}