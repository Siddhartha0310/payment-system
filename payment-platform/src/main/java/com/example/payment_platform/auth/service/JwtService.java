package com.example.payment_platform.auth.service;

import java.nio.charset.StandardCharsets;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;


import org.springframework.stereotype.Service;

import com.example.payment_platform.auth.config.JwtConfig;
import com.example.payment_platform.auth.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service 

public class JwtService {
private final JwtConfig jwtConfig;
private final SecretKey signingKey;

 
public JwtService(JwtConfig jwtConfig){
    this.jwtConfig = jwtConfig;
    
    String secret = jwtConfig.getSecret();

    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);

    this.signingKey = Keys.hmacShaKeyFor(keyBytes);
   
}
  public String generateeAccessToken(User user){
     
        Instant now = Instant.now();

        Instant expiry = now.plusSeconds(jwtConfig.getAccessTokenExpiry());

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(signingKey)
                .compact();
      }

   public String generateRefreshToken(User user) {

    Instant now = Instant.now();
    Instant expiry = now.plusSeconds(jwtConfig.getRefreshTokenExpiry());

    return Jwts.builder()
            .subject(user.getId().toString())
            .id(UUID.randomUUID().toString())   // jti
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiry))
            .signWith(signingKey)
            .compact();
}  
   private Claims parseClaims(String token){
    return Jwts.parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
   }
   public void validate(String token){
       parseClaims(token);
   }
   public UUID extractUserId(String token){
     Claims claims = parseClaims(token);
     return UUID.fromString(claims.getSubject());
   }
}
