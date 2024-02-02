package com.example.assessment.controllers;
import com.example.assessment.DTO.RequestLoginDTO;
import com.example.assessment.entities.Users;
import com.example.assessment.filter.CustomAuthProvider;
import com.example.assessment.filter.TokenGeneratorFilter;
import com.example.assessment.repositories.UsersRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/user")
public class JWTLogin {

    private final CustomAuthProvider customAuthProvider;
    private final TokenGeneratorFilter tokenGeneratorFilter;

    UsersRepo usersRepo;

    @Autowired
    public JWTLogin(CustomAuthProvider customAuthProvider, TokenGeneratorFilter tokenGeneratorFilter , UsersRepo userRepo) {
        this.customAuthProvider = customAuthProvider;
        this.tokenGeneratorFilter = tokenGeneratorFilter;
        this.usersRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RequestLoginDTO requestLoginDTO) {
        String name = requestLoginDTO.getUserName();
        Users user = usersRepo.findById(name).orElseThrow(() -> new EntityNotFoundException("User not found with id:"));
        if(!Objects.equals(user.getUserName(), name)){
            throw new RuntimeException("This is an invalid user");
        }
        try {
            Authentication authentication = customAuthProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(requestLoginDTO.getUserName(), requestLoginDTO.getPassword()));
            String token = tokenGeneratorFilter.generateJWT(authentication);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
