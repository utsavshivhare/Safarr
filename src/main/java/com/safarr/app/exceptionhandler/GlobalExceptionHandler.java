package com.safarr.app.exceptionhandler;

import com.safarr.app.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

/**
 * Global exception handler to handle exceptions and return structured error responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handles ValidationException and returns a structured error response.
     *
     * @param ex the ValidationException
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(AppException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                ex.getStatus().value(),
                Timestamp.valueOf(LocalDateTime.now()).toString()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    /**
     * Handles RuntimeException and returns a structured error response.
     *
     * @param ex the RuntimeException
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Timestamp.valueOf(LocalDateTime.now()).toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles Exception and returns a structured error response.
     *
     * @param ex the Exception
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Timestamp.valueOf(LocalDateTime.now()).toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles MethodArgumentNotValidException and returns a structured error response.
     *
     * @param ex the MethodArgumentNotValidException
     * @return ResponseEntity containing the error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(
                errorMessage,
                HttpStatus.BAD_REQUEST.value(),
                Timestamp.valueOf(LocalDateTime.now()).toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
