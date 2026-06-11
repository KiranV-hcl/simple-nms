package com.hcl.simple_nms.controller;

import com.hcl.simple_nms.dto.ApiResponse;
import com.hcl.simple_nms.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and generate JWT token")
    public ApiResponse<String> login(@RequestParam String username,
                                     @RequestParam String password) {

        // Basic authentication (POC)
        if ("admin".equals(username) && "admin".equals(password)) {

            // TOKEN GENERATED HERE
            String token = jwtUtil.generateToken(username);

            return new ApiResponse<>("Login Success", token);
        }

        return new ApiResponse<>("Invalid credentials", null);
    }
}