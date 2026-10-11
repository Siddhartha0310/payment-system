package com.example.payment_platform.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest( @NotBlank String refreshToken) {

}
