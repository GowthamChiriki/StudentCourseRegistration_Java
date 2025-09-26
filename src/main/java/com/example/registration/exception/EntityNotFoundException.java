package com.example.registration.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message){
        super(message);
    }
}
