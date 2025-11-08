package org.example.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception, HttpServletRequest request)

    {

        ErrorResponse response =new ErrorResponse(HttpStatus.NOT_FOUND.value(),"ResourceNotfound",exception.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> ValidationException(ValidationException exception, HttpServletRequest request)

    {

        ErrorResponse response =new ErrorResponse(HttpStatus.NOT_FOUND.value(),"ResourceNotfound",exception.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

}
