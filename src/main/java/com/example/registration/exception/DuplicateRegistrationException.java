package com.example.registration.exception;

public class DuplicateRegistrationException extends RuntimeException{
    public DuplicateRegistrationException(String message){
        super(message);
    }
}
