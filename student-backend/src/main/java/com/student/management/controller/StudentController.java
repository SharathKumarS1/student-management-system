package com.student.management.controller;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.student.management.dto.StudentRequest;
import com.student.management.dto.StudentResponse;
import com.student.management.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService service;

    @PostMapping
    public StudentResponse createStudent(@Valid @RequestBody StudentRequest request) {
        return service.createStudent(request);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return service.getAllStudents();
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        return service.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}