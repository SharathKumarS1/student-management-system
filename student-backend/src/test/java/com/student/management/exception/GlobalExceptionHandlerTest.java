package com.student.management.exception;

import com.student.management.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldHandleResourceNotFoundException() {

        ResourceNotFoundException exception =
                new ResourceNotFoundException(
                        "Student not found"
                );

        ResponseEntity<String> response =
                handler.handleNotFound(exception);

        assertEquals(
                HttpStatus.NOT_FOUND,
                response.getStatusCode()
        );

        assertEquals(
                "Student not found",
                response.getBody()
        );
    }

    @Test
    void shouldHandleUserAlreadyExistsException() {

        UserAlreadyExistsException exception =
                new UserAlreadyExistsException(
                        "User already exists"
                );

        ResponseEntity<ErrorResponse> response =
                handler.handleUserAlreadyExists(exception);

        assertEquals(
                HttpStatus.CONFLICT,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        ErrorResponse errorResponse =
                response.getBody();

        assertEquals(
                "USER_ALREADY_EXISTS",
                errorResponse.getErrorCode()
        );

        assertEquals(
                "User already exists",
                errorResponse.getMessage()
        );

        assertNotNull(
                errorResponse.getTimestamp()
        );
    }
}