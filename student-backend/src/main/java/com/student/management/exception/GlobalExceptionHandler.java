package com.student.management.exception;

import java.time.LocalDateTime;
import com.student.management.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

     @ExceptionHandler(
            UserAlreadyExistsException.class
    )
    public ResponseEntity<ErrorResponse>
    handleUserAlreadyExists(
            UserAlreadyExistsException ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        "USER_ALREADY_EXISTS",
                        ex.getMessage(),
                        LocalDateTime.now()
                );


        return ResponseEntity
                .status(
                        HttpStatus.CONFLICT
                )
                .body(
                        errorResponse
                );
    }
}