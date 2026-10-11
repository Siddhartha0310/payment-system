package com.example.payment_platform.auth.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(){
        super("Invalid credentials");
    }

}
