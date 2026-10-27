package com.student.management.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.student.management.dto.StudentRequest;
import com.student.management.dto.StudentResponse;
import com.student.management.entity.Student;
import com.student.management.exception.ResourceNotFoundException;
import com.student.management.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .phoneNumber(request.getPhoneNumber())
                .build();

        Student savedStudent = repository.save(student);

        return mapToResponse(savedStudent);
    }

    @Override
    public StudentResponse getStudent(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        return mapToResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse updateStudent(Long id,
                                         StudentRequest request) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());
        student.setPhoneNumber(request.getPhoneNumber());

        Student updatedStudent = repository.save(student);

        return mapToResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id));

        repository.delete(student);
    }

    private StudentResponse mapToResponse(Student student) {

        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .department(student.getDepartment())
                .phoneNumber(student.getPhoneNumber())
                .build();
    }
}