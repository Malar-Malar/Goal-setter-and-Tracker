package com.example.Goal.Setter.Tracker.service;

import com.example.Goal.Setter.Tracker.model.User;
import com.example.Goal.Setter.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Signup method
    public User signup(User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        return userRepository.save(user); // Save user to DB
    }

    // Login method
    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
