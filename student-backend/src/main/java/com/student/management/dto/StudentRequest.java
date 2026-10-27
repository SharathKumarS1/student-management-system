package com.student.management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequest {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100,
            message = "Customer name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    private String department;

    private String phoneNumber;
}