package com.example.springjpa.controller;
import com.example.springjpa.entities.ShiftType;
import com.example.springjpa.services.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shift-type")
public class ShiftTypeController {

    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.addShiftType(shiftType);
        return ResponseEntity.ok("The ShiftType is Added");
    }

}
