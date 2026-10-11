package com.example.payment_platform.auth.exception;

public class UserAlreadyExistingException extends RuntimeException {
        public UserAlreadyExistingException(String email){
                 super("User already exists with email: " + email);
        }
}
