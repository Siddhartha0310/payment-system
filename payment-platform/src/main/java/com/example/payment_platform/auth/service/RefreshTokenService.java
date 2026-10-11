package com.example.payment_platform.auth.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.payment_platform.auth.config.JwtConfig;
import com.example.payment_platform.auth.entity.RefreshToken;
import com.example.payment_platform.auth.entity.User;
import com.example.payment_platform.auth.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtConfig jwtConfig;

    public void save(User user, String token) {

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(token)
                .expiresAt(
                        Instant.now()
                               .plusSeconds(jwtConfig.getRefreshTokenExpiry())
                )
                .deviceName("Unknown")
                .build();

        refreshTokenRepository.save(refreshToken);
    }
    public Optional<RefreshToken> findByToken(String token){
        
    return refreshTokenRepository.findByToken(token); 
    }
}

