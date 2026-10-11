package com.example.payment_platform.auth.exception;

public class InvalidRefreshToken extends RuntimeException{
    public InvalidRefreshToken(){
        super("Refresh token invalid");
    }
}
