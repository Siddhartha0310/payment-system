package com.example.payment_platform.auth.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_platform.auth.dto.LoginRequest;
import com.example.payment_platform.auth.dto.LoginResponse;
import com.example.payment_platform.auth.dto.MeResponse;
import com.example.payment_platform.auth.dto.RefreshTokenRequest;
import com.example.payment_platform.auth.dto.RegisterRequest;
import com.example.payment_platform.auth.dto.RegisterResponse;
import com.example.payment_platform.auth.entity.User;
import com.example.payment_platform.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor 
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse response = authService.register(request);
        return   ResponseEntity.status(HttpStatus.CREATED)
                     .body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@Valid @RequestBody LoginRequest request ){
        LoginResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
        .body(response);
    }
    @GetMapping("/me")
    public ResponseEntity<MeResponse>me(@AuthenticationPrincipal User user){
         MeResponse response = new MeResponse(user.getId(),user.getEmail());
         return ResponseEntity.status(HttpStatus.OK)
         .body(response);
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<LoginResponse> refreshTokenRequest(@Valid @RequestBody RefreshTokenRequest request){
        LoginResponse response = authService.refresh(request);
        return ResponseEntity.status(HttpStatus.OK)
               .body(response);
    }
    
    
    
    

}

