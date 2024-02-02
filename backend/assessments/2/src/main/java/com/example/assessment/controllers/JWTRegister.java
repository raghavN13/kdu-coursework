package com.example.assessment.controllers;

import com.example.assessment.DTO.RegisterAuthDTO;
import com.example.assessment.entities.Users;
import com.example.assessment.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class JWTRegister {
    UserService userService;
    PasswordEncoder passwordEncoder;

    public JWTRegister(UserService userService,PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody RegisterAuthDTO registerAuthDTO){
        Users user = new Users();
        user.setUserName(registerAuthDTO.getUserName());
        user.setPassword(passwordEncoder.encode(registerAuthDTO.getPassword()));
        user.setEmail(registerAuthDTO.getEmail());
        user.setRole(registerAuthDTO.getRole());

        userService.addUser(user);
        return  ResponseEntity.ok("The User is Registered");
    }
}
