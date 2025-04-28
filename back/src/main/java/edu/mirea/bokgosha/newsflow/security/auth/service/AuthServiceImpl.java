package edu.mirea.bokgosha.newsflow.security.auth.service;

import edu.mirea.bokgosha.newsflow.security.auth.dto.LoginRequest;
import edu.mirea.bokgosha.newsflow.security.auth.dto.LoginResponse;
import edu.mirea.bokgosha.newsflow.security.auth.jwt.JwtIssuer;
import edu.mirea.bokgosha.newsflow.security.auth.principal.UserPrincipal;
import edu.mirea.bokgosha.newsflow.user.entity.User;
import edu.mirea.bokgosha.newsflow.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public LoginResponse login(LoginRequest request) {
        final String email = request.getUsername();
        if (!userService.userExistByEmail(email)) {
            return LoginResponse.builder()
                    .token(null)
                    .build();
        }
        return getToken(request);
    }

    @Override
    public LoginResponse register(LoginRequest request) {
        final String email = request.getUsername();
        if (userService.userExistByEmail(email)) {
            return LoginResponse.builder()
                    .token(null)
                    .build();
        }

        userService.addUser(User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build());
        return getToken(request);
    }

    private LoginResponse getToken(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getUsername(), roles);

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
