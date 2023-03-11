// This code defines a custom exception class that extends the RuntimeException class and includes a constructor that takes a message parameter. This class is used to provide a consistent and meaningful way of handling situations where a name exceeds a specified length.

package com.example.les12services.exception;

public class NameTooLongException extends RuntimeException{
    public NameTooLongException(String message){
        super(message);
    }
}
