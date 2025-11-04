package com.microservice.authentication.controller;

import com.microservice.authentication.service.JwtService;
import com.microservice.authentication.service.UserAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthController {

    private final JwtService jwtService;
    private final UserAuthService userAuthService; // bạn implement authenticate

    public AuthController(JwtService jwtService, UserAuthService userAuthService) {
        this.jwtService = jwtService;
        this.userAuthService = userAuthService;
    }

    public static record LoginRequest(String username, String password) {}

    public static record TokenResponse(String token, long expiresInMs) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
//        var user = userAuthService.authenticate(req.username(), req.password());
//        if (user == null) {
//            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
//        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", "ADMIN");
        claims.put("userId",1);

        String token = jwtService.generateToken(req.username(), claims);

        return ResponseEntity.ok(new TokenResponse(token, 6000));
    }

    @GetMapping("/check")
    public String check(){
        return "Hello World";
    }
}