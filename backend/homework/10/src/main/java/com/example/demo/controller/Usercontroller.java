package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Usercontroller {
    @Autowired
    private UserService userService;
    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getusers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/getusers/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name){
    return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PostMapping("/createuser")
    public ResponseEntity<User>addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

}
