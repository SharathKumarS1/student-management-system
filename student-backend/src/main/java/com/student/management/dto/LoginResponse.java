package com.student.management.dto;

import com.student.management.enums.Role;


public class LoginResponse {

    private String token;

    private String tokenType;

    private String email;

    private Role role;


    public LoginResponse(
            String token,
            String tokenType,
            String email,
            Role role) {

        this.token = token;
        this.tokenType = tokenType;
        this.email = email;
        this.role = role;
    }


    public String getToken() {
        return token;
    }


    public String getTokenType() {
        return tokenType;
    }


    public String getEmail() {
        return email;
    }


    public Role getRole() {
        return role;
    }
}