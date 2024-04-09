package com.example.springjpa.controller;
import com.example.springjpa.entities.Shifts;
import com.example.springjpa.services.ShiftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shifts")
public class ShiftsController {

    private final ShiftsService shiftsService;

    @Autowired
    public ShiftsController(ShiftsService shiftsService) {
        this.shiftsService = shiftsService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addShift(@RequestBody Shifts shift) {
        shiftsService.addShift(shift);
        return ResponseEntity.ok("The Shift is Added");
    }

}
