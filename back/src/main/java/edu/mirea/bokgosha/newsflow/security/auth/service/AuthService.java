package edu.mirea.bokgosha.newsflow.security.auth.service;

import edu.mirea.bokgosha.newsflow.security.auth.dto.LoginRequest;
import edu.mirea.bokgosha.newsflow.security.auth.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    LoginResponse register(LoginRequest request);
}
