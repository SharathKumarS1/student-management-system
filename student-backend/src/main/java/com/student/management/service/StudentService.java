package com.student.management.service;

import java.util.List;

import com.student.management.dto.StudentRequest;
import com.student.management.dto.StudentResponse;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    StudentResponse getStudent(Long id);

    List<StudentResponse> getAllStudents();

    StudentResponse updateStudent(Long id,
                                  StudentRequest request);

    void deleteStudent(Long id);
}