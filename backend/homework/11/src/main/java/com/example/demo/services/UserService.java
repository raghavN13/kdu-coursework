// UserService.java
// UserService.java
package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(UserDTO userDTO) {
        userRepo.addUser(userDTO);
    }

    public User getUserById(UUID userId) {
        return userRepo.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }
}

