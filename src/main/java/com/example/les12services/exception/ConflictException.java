// This code defines a custom exception class that extends the RuntimeException class and includes an annotation that specifies the HTTP status code of the response when the exception is thrown. This class is used to provide a consistent and meaningful way of handling conflicts in the application.

package com.example.les12services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//The @ResponseStatus(HttpStatus.CONFLICT) annotation is used to indicate that if an instance of this exception is thrown, the HTTP status code of the response should be 409 CONFLICT.
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    //The class includes a constructor that takes a single parameter, a String message.
    public ConflictException(String message) {
        super(message);
    }

    //The purpose of this class is to provide a custom exception that can be thrown when there is a conflict in the application, such as when a resource is already in use or a transaction cannot be completed due to conflicting data.

}
