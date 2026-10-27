package com.student.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {

    private Long id;

    private String name;

    private String email;

    private String department;

    private String phoneNumber;
}