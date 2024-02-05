package com.kdu.smarthome.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_table")
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = LocalDateTime.now();

    @Column(name="updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    private String role = "ROLE_USER";

    @ManyToMany
    private List<Houses> house = new ArrayList<>();

}
