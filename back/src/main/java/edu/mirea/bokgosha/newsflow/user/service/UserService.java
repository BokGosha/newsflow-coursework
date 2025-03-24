package edu.mirea.bokgosha.newsflow.user.service;

import edu.mirea.bokgosha.newsflow.user.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    User addUser(User user);

    User getUserById(Long id);

    boolean userExistByEmail(String email);
}
