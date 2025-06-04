package com.example.Goal.Setter.Tracker.repository;

import com.example.Goal.Setter.Tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email); // 🔧 Add this line
}
