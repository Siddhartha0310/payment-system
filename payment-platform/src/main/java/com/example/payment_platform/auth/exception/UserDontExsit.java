package com.example.payment_platform.auth.exception;

public class UserDontExsit extends RuntimeException {
   public  UserDontExsit(String email){
    super("User doesn't exists with email: " + email);
   }
}
