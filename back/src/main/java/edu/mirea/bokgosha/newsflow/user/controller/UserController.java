package edu.mirea.bokgosha.newsflow.user.controller;

import edu.mirea.bokgosha.newsflow.security.auth.principal.UserPrincipal;
import edu.mirea.bokgosha.newsflow.user.entity.User;
import edu.mirea.bokgosha.newsflow.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public User getUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        long userId = userPrincipal.getUserId();
        return userService.getUserById(userId);
    }
}
