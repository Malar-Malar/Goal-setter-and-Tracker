package com.example.Goal.Setter.Tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    // Getters and Setters
}
