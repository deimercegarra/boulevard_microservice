package com.pragma.boulevard_microservice.infrastructure.exceptionhandler;

import com.pragma.boulevard_microservice.domain.exception.DomainException;
import com.pragma.boulevard_microservice.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.boulevard_microservice.domain.exception.UserNotFoundException;
import com.pragma.boulevard_microservice.infrastructure.exception.CategoryNotFoundException;
import com.pragma.boulevard_microservice.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,
                        ignoredNoDataFoundException.getMessage() != null ? ignoredNoDataFoundException.getMessage() : "No data found for the requested petition"));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(
            CategoryNotFoundException ignoredCategoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,
                        ignoredCategoryNotFoundException.getMessage() != null ? ignoredCategoryNotFoundException.getMessage() : "Category not found."));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundException(
            RestaurantNotFoundException ignoredRestaurantNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,
                        ignoredRestaurantNotFoundException.getMessage() != null ? ignoredRestaurantNotFoundException.getMessage() : "Restaurant not found."));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> handleDomainException(
            DomainException ignoredDomainException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,
                        ignoredDomainException.getMessage() != null ? ignoredDomainException.getMessage() : "No data found for the requested petition"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException ignoredUserNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,
                        ignoredUserNotFoundException.getMessage() != null ? ignoredUserNotFoundException.getMessage() : "User not found."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandlerExceptionResolve(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<String, String>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
    
}