package com.example.demo.controller;

import com.example.demo.dto.ShiftUserDTO;
import com.example.demo.entities.ShiftUser;
import com.example.demo.services.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shift-user")
public class ShiftUserController {

    private final ShiftUserService shiftUserService;

    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDTO shiftUserDTO) {
        shiftUserService.addShiftUser(shiftUserDTO);
        return ResponseEntity.ok("The ShiftUser is Added");
    }

    @GetMapping("/get/{shiftUserId}")
    public ResponseEntity<ShiftUser> getShiftUser(@PathVariable UUID shiftUserId) {
        ShiftUser shiftUser = shiftUserService.getShiftUserById(shiftUserId);
        if (shiftUser != null) {
            return ResponseEntity.ok(shiftUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShiftUser>> getAllShiftUsers() {
        List<ShiftUser> shiftUsers = shiftUserService.getAllShiftUsers();
        return ResponseEntity.ok(shiftUsers);
    }
}
