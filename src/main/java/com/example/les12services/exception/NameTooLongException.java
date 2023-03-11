package com.example.les12services.exception;

public class NameTooLongException extends RuntimeException{
    public NameTooLongException(String message){
        super(message);
    }
}
