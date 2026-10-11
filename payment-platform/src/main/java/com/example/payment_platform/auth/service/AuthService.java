package com.example.payment_platform.auth.service;

import com.example.payment_platform.auth.config.JwtConfig;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.payment_platform.auth.dto.LoginRequest;
import com.example.payment_platform.auth.dto.LoginResponse;
import com.example.payment_platform.auth.dto.RefreshTokenRequest;
import com.example.payment_platform.auth.dto.RegisterRequest;
import com.example.payment_platform.auth.dto.RegisterResponse;
import com.example.payment_platform.auth.entity.User;
import com.example.payment_platform.auth.exception.InvalidCredentialsException;
import com.example.payment_platform.auth.exception.InvalidRefreshToken;
import com.example.payment_platform.auth.exception.UserAlreadyExistingException;
import com.example.payment_platform.auth.exception.UserDontExsit;
import com.example.payment_platform.auth.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class AuthService {
   private final JwtConfig jwtConfig;
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;
   private final RefreshTokenService refreshTokenService;
   private final UserService userService;
   
   @Transactional 
   public RegisterResponse register(RegisterRequest request){

       if(userRepository.existsByEmail(request.email())){
        throw new UserAlreadyExistingException(request.email());
       }
       String passwordHash = passwordEncoder.encode(request.password());
    
       User user = User.register(request.email(), passwordHash);
       User savedUser = userRepository.save(user);
       return new RegisterResponse(savedUser.getId(),savedUser.getEmail());
   }

   @Transactional 
   public LoginResponse login(LoginRequest loginRequest){
    User user = userRepository.findByEmail(loginRequest.email())
    .orElseThrow(() -> new UserDontExsit(loginRequest.email()));
     if(!passwordEncoder.matches(loginRequest.password(),user.getPasswordHash())){
        throw new  InvalidCredentialsException();
     }
     String accessToken = jwtService.generateeAccessToken(user);
     String refreshToken = jwtService.generateRefreshToken(user);
     refreshTokenService.save(user, refreshToken);
    return new LoginResponse(accessToken,refreshToken,"Bearer",
            jwtConfig.getAccessTokenExpiry());
   }
  @Transactional 
  public  LoginResponse refresh(RefreshTokenRequest refreshTokenRequest){
        String token = refreshTokenRequest.refreshToken();
        jwtService.validate(token);
        UUID userId = jwtService.extractUserId(token);
        refreshTokenService.findByToken(token).orElseThrow(()->new InvalidRefreshToken());
        User user = userService.loadUserById1(userId);
          
        String naccessToken = jwtService.generateeAccessToken(user);
     
       
        return new LoginResponse(naccessToken,token,"Bearer",
            jwtConfig.getAccessTokenExpiry());
  }
}
