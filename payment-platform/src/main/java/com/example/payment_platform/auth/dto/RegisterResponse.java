package com.example.payment_platform.auth.dto;

import java.util.UUID;

public record RegisterResponse(  
    
    UUID id,

    String email) {

}
