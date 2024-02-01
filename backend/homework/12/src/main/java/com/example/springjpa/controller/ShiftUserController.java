package com.example.springjpa.controller;

import com.example.springjpa.entities.ShiftUser;
import com.example.springjpa.services.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shift-user")
public class ShiftUserController {

    private final ShiftUserService shiftUserService;

    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.addShiftUser(shiftUser);
        return ResponseEntity.ok("The ShiftUser is Added");
    }

}
