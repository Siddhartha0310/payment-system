package com.example.payment_platform.auth.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistingException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExist(UserAlreadyExistingException ex, HttpServletRequest request){
        ApiError error = ApiError.builder()
        .timestamp(Instant.now())
            .status(HttpStatus.CONFLICT.value())
            .error(HttpStatus.CONFLICT.getReasonPhrase())
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .fieldErrors(Map.of())
            .build();
        return  ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error);
         }

     @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex,HttpServletRequest request) {
        // 1. Get the BindingResult
        BindingResult bindingResult = ex.getBindingResult();
        
        // 2. Build the Map<String, String> for field errors
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }    
       ApiError error = ApiError.builder()
                        .timestamp(Instant.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .fieldErrors(errors)
            .build();

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body(error);    
    }
    }


