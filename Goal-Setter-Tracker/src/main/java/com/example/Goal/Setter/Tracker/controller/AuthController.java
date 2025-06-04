package com.example.Goal.Setter.Tracker.controller;

import com.example.Goal.Setter.Tracker.dto.LoginRequest;
import com.example.Goal.Setter.Tracker.dto.SignupRequest;
import com.example.Goal.Setter.Tracker.model.User;
import com.example.Goal.Setter.Tracker.repository.UserRepository;
import com.example.Goal.Setter.Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        userRepository.save(user); // password stored as plain-text
        return ResponseEntity.ok("User registered successfully");
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmailAndPassword(
                user.getEmail(), user.getPassword()
        );

        if (existingUser.isPresent()) {
            return ResponseEntity.ok(existingUser.get()); // ✅ return full user as JSON
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }

}

