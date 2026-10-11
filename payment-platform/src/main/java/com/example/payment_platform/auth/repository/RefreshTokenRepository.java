package com.example.payment_platform.auth.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payment_platform.auth.entity.RefreshToken;
import com.example.payment_platform.auth.entity.User;



public interface RefreshTokenRepository extends JpaRepository<RefreshToken,UUID> {
   Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findByUser(User user);

    void deleteByUser(User user);
}
