package edu.mirea.bokgosha.newsflow.security.auth.controller;

import edu.mirea.bokgosha.newsflow.security.auth.dto.LoginRequest;
import edu.mirea.bokgosha.newsflow.security.auth.dto.LoginResponse;
import edu.mirea.bokgosha.newsflow.security.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public LoginResponse register(@Valid @RequestBody LoginRequest request) {
        return authService.register(request);
    }
}
