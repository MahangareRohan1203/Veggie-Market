package com.vegetablemart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> globalExceptionsHandler(Exception ie, WebRequest req) {

        MyErrorDetails err = new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ie.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    //to handle Not found exception
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noHandlerFound(NoHandlerFoundException nfe, WebRequest req) {

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
