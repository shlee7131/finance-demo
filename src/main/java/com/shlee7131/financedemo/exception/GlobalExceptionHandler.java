package com.shlee7131.financedemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;
import java.time.LocalDate;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException e){
        return responseBuild(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(BadRequestException e){
        return responseBuild(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Error> handleValidationException(ValidationException e) {
        System.out.println(e.toString());
        return responseBuild(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return responseBuild(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleInternalServerException(Exception e) {
        return responseBuild(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Error> responseBuild(String message, HttpStatus statusCode){
        Error error = new Error();
        error.setMessage(message);
        error.setStatusCode(statusCode.value());
        error.setCreated_at(LocalDate.now());
        log.error("error: {}", error);
        return new ResponseEntity<>(error, statusCode);
    }
}
