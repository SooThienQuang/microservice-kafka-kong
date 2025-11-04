package com.microservice.authentication.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthService {
    // demo: hardcode user
    public User authenticate(String username, String password) {
        if ("admin".equals(username) && "123".equals(password)) {
            return new User(1L, "admin", List.of("ROLE_ADMIN"));
        }
        return null;
    }

    public static record User(Long id, String username, List<String> roles) {}
}