package com.example.assessment.services;

import com.example.assessment.entities.Users;
import com.example.assessment.repositories.UsersRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepo userRepo;

    @Autowired
    public UserService(UsersRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(Users user) {
        userRepo.save(user);
    }

    public Users getUsername(String name){
       return userRepo.findById(name).orElseThrow(() -> new EntityNotFoundException("User not found with id: "+name));
    }
}
