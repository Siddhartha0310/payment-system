package com.example.payment_platform.auth.exception;

import java.time.Instant;
import java.util.Map;

import lombok.Builder;

@Builder
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path ,
        Map<String,String>fieldErrors
) {
}