package edu.mirea.bokgosha.newsflow.security.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties properties;

    public String issue(long userId, String username, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(7, ChronoUnit.DAYS)))
                .withClaim("username", username)
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC256(properties.getSecretSey()));
    }
}
