package com.nithin.springsecuritypractice.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.nithin.springsecuritypractice.dto.JwtAuthResponse;
import com.nithin.springsecuritypractice.dto.LoginDto;
import com.nithin.springsecuritypractice.dto.RegisterDto;
import com.nithin.springsecuritypractice.entity.User;
import com.nithin.springsecuritypractice.service.AuthService;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        User user = authService.register(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Perform any additional cleanup or logging out logic here

        // Invalidate the current user's session
        SecurityContextHolder.clearContext();
        // You might want to clear cookies or tokens here, depending on your authentication mechanism

        return ResponseEntity.ok("Logout successful");
    }
}
