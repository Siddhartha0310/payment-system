package com.example.payment_platform.auth.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.payment_platform.auth.entity.User;
import com.example.payment_platform.auth.exception.UserDontExsit;
import com.example.payment_platform.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + email));
    }

    public UserDetails loadUserById(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"));
    }

    public User loadUserById1 (UUID id){
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
