package com.example.FakeECommerce.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GlobalExceptionWrapper> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new GlobalExceptionWrapper(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalExceptionWrapper> handleException(Exception ex) {
        return new ResponseEntity<>(new GlobalExceptionWrapper(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
