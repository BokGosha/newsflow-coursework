package edu.mirea.bokgosha.newsflow.security.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class LoginRequest {

    @NotNull
    private String username;

    @ToString.Exclude
    @NotEmpty
    private String password;
}
