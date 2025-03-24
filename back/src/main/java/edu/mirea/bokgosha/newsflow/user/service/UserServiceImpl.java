package edu.mirea.bokgosha.newsflow.user.service;

import edu.mirea.bokgosha.newsflow.user.entity.User;
import edu.mirea.bokgosha.newsflow.user.entity.UserRole;
import edu.mirea.bokgosha.newsflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUsernameIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    @Override
    public User addUser(User user) {
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean userExistByEmail(String email) {
        return userRepository.existsByUsernameIgnoreCase(email);
    }
}
