package com.example.payment_platform.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component 
@ConfigurationProperties(prefix = "jwt")
@Getter 
@Setter  
public class JwtConfig {
private String secret;

private long accessTokenExpiry;

private long refreshTokenExpiry;

}
