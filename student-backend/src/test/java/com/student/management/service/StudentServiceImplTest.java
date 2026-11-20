package com.student.management.service;

import com.student.management.dto.StudentRequest;
import com.student.management.dto.StudentResponse;
import com.student.management.entity.Student;
import com.student.management.exception.ResourceNotFoundException;
import com.student.management.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void shouldCreateStudent() {

        StudentRequest request = new StudentRequest();

        request.setName("Sharath");
        request.setEmail("sharath@gmail.com");
        request.setDepartment("CSE");
        request.setPhoneNumber("9876543210");

        Student student = Student.builder()
                .id(1L)
                .name("Sharath")
                .email("sharath@gmail.com")
                .department("CSE")
                .phoneNumber("9876543210")
                .build();

        when(repository.save(any(Student.class)))
                .thenReturn(student);

        StudentResponse response =
                studentService.createStudent(request);

        assertNotNull(response);
        assertEquals("Sharath", response.getName());

        verify(repository, times(1))
                .save(any(Student.class));
    }

    @Test
    void shouldGetStudentById() {

        Student student = Student.builder()
                .id(1L)
                .name("Sharath")
                .email("sharath@gmail.com")
                .department("CSE")
                .phoneNumber("9876543210")
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(student));

        StudentResponse response =
                studentService.getStudent(1L);

        assertEquals("Sharath", response.getName());
    }

    @Test
    void shouldThrowExceptionWhenStudentNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> studentService.getStudent(1L)
        );
    }

    @Test
    void shouldGetAllStudents() {

        Student student = Student.builder()
                .id(1L)
                .name("Sharath")
                .email("sharath@gmail.com")
                .department("CSE")
                .phoneNumber("9876543210")
                .build();

        when(repository.findAll())
                .thenReturn(List.of(student));

        List<StudentResponse> students =
                studentService.getAllStudents();

        assertEquals(1, students.size());
    }

    @Test
    void shouldUpdateStudent() {

        Student existingStudent = Student.builder()
                .id(1L)
                .name("Old")
                .email("old@gmail.com")
                .department("ECE")
                .phoneNumber("1111111111")
                .build();
        
        StudentRequest request = new StudentRequest();

                request.setName("New");
                request.setEmail("new@gmail.com");
                request.setDepartment("CSE");
                request.setPhoneNumber("9999999999");

        Student updatedStudent = Student.builder()
                .id(1L)
                .name("New")
                .email("new@gmail.com")
                .department("CSE")
                .phoneNumber("9999999999")
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(existingStudent));

        when(repository.save(any(Student.class)))
                .thenReturn(updatedStudent);

        StudentResponse response =
                studentService.updateStudent(1L, request);

        assertEquals("New", response.getName());
    }

    @Test
    void shouldDeleteStudent() {

        Student student = Student.builder()
                .id(1L)
                .name("Sharath")
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(repository, times(1))
                .delete(student);
    }
}