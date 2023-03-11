//This class is a global exception handler for the application that handles specific types of exceptions thrown by the controllers. It uses the Spring framework to handle the exceptions and return appropriate HTTP responses.

package com.example.les12services.controller;

import com.example.les12services.exception.NameTooLongException;
import com.example.les12services.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice is indicating that it is a global exception handler for all controllers in the application.
@ControllerAdvice
public class ExceptionController {

    //The first method, handleResourceNotFoundException(), handles exceptions of type ResourceNotFoundException. When such an exception is thrown, this method returns a ResponseEntity object containing the exception message and an HTTP status code of NOT_FOUND.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //The second method, handleNameTooLongException(), handles exceptions of type NameTooLongException. When such an exception is thrown, this method returns a ResponseEntity object containing the exception message and an HTTP status code of BAD_REQUEST.
    @ExceptionHandler(NameTooLongException.class)
    public ResponseEntity<String>
    handleNameTooLongException
            (NameTooLongException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //Both of these methods take a single parameter of the specific exception type that they handle.


}
