package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddingUsers implements CommandLineRunner {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDAO userDAO;

    //Username password email role

    @Override
    public void run(String... args) throws Exception {
        userDAO.addUser(new User("raghav",passwordEncoder.encode("raghav123"),"raghav.com","ROLE_ADMIN"));
        userDAO.addUser(new User("nakul",passwordEncoder.encode("nakul123"),"nakul.com","ROLE_BASIC"));


    }
}
