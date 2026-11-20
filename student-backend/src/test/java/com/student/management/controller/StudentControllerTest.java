package com.student.management.controller;

import com.student.management.dto.StudentRequest;
import com.student.management.dto.StudentResponse;
import com.student.management.service.StudentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void shouldCreateStudent() {

        StudentRequest request = new StudentRequest();
        request.setName("Sharath");

        StudentResponse response =
                StudentResponse.builder()
                        .id(1L)
                        .name("Sharath")
                        .build();

        when(studentService.createStudent(request))
                .thenReturn(response);

        StudentResponse result =
                studentController.createStudent(request);

        assertEquals("Sharath", result.getName());
    }

    @Test
    void shouldGetStudent() {

        StudentResponse response =
                StudentResponse.builder()
                        .id(1L)
                        .name("Sharath")
                        .build();

        when(studentService.getStudent(1L))
                .thenReturn(response);

        StudentResponse result =
                studentController.getStudent(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void shouldGetAllStudents() {

        StudentResponse response =
                StudentResponse.builder()
                        .id(1L)
                        .name("Sharath")
                        .build();

        when(studentService.getAllStudents())
                .thenReturn(List.of(response));

        List<StudentResponse> result =
                studentController.getAllStudents();

        assertEquals(1, result.size());
    }

    @Test
    void shouldUpdateStudent() {

        StudentRequest request =
                new StudentRequest();

        request.setName("Updated");

        StudentResponse response =
                StudentResponse.builder()
                        .id(1L)
                        .name("Updated")
                        .build();

        when(studentService.updateStudent(
                eq(1L),
                any(StudentRequest.class)
        )).thenReturn(response);

        StudentResponse result =
                studentController.updateStudent(
                        1L,
                        request
                );

        assertEquals(
                "Updated",
                result.getName()
        );
    }

    @Test
    void shouldDeleteStudent() {

        doNothing()
                .when(studentService)
                .deleteStudent(1L);

        studentController.deleteStudent(1L);

        verify(studentService)
                .deleteStudent(1L);
    }
}