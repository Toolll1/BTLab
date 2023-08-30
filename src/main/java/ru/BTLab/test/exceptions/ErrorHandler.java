package ru.BTLab.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleConflictException(final ConflictException e) {

        return new ResponseEntity<>(
                Map.of("message", e.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleBadRequest(final BadRequestException e) {

        return new ResponseEntity<>(
                Map.of("message", e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleObjectNotFound(final ObjectNotFoundException e) {

        return new ResponseEntity<>(
                Map.of("message", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleRemainingErrors(final RuntimeException e) {

        return new ResponseEntity<>(
                Map.of("message", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
