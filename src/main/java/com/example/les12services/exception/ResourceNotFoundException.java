//This code defines a custom exception class that extends the RuntimeException class and includes a constructor that takes a message parameter. This class is used to provide a consistent and meaningful way of handling situations where a requested resource cannot be found.

package com.example.les12services.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
