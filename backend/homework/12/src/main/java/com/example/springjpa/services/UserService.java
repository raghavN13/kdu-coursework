// UserService.java
// UserService.java
package com.example.springjpa.services;

import com.example.springjpa.entities.User;
import com.example.springjpa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(User user) {
        userRepo.save(user);
    }

}

