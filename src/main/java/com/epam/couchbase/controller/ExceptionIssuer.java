package com.epam.couchbase.controller;

import com.epam.couchbase.exception.ExceptionResponse;
import com.epam.couchbase.exception.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionIssuer {
    private static final int CUSTOM_CODE_MULTIPLIER = 100;

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ExceptionResponse> issueNoSuchEntityException() {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .code(HttpStatus.NOT_FOUND.value() * CUSTOM_CODE_MULTIPLIER)
                .message(NoSuchEntityException.class.getSimpleName())
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
