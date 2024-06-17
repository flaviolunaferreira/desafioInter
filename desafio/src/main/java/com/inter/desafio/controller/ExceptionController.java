package com.inter.desafio.controller;

import com.inter.desafio.exception.DuplicateValue;
import com.inter.desafio.exception.NotFound;
import com.inter.desafio.exception.StandardError;
import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<StandardError> notFoundException(NotFound e, ServletRequest request) {
        StandardError error = new StandardError (
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateValue.class)
    public ResponseEntity<StandardError> integratyViolationException(DuplicateValue e, ServletRequest request) {
        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

}
