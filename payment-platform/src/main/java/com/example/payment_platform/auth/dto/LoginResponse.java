package com.example.payment_platform.auth.dto;


public record LoginResponse(
       String accessToken,

        String refreshToken,

        String tokenType,

        long expiresIn
) {

}
