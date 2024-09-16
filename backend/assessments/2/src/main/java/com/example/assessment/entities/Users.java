package com.example.assessment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_register_table")
public class Users {
    @Id
    @Column(name = "user_name")
    String userName;

    String password;
    String email;
    String role;
}
