package edu.mirea.bokgosha.newsflow.security.auth.principal;

import edu.mirea.bokgosha.newsflow.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findUserByEmail(username);
        return UserPrincipal.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().toString())))
                .password(user.getPassword())
                .build();
    }
}
