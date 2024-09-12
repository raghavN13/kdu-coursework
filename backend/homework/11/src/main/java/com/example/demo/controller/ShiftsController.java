package com.example.demo.controller;
import com.example.demo.dto.ShiftsDTO;
import com.example.demo.entities.Shifts;
import com.example.demo.services.ShiftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shifts")
public class ShiftsController {

    private final ShiftsService shiftsService;

    @Autowired
    public ShiftsController(ShiftsService shiftsService) {
        this.shiftsService = shiftsService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addShift(@RequestBody ShiftsDTO shiftsDTO) {
        shiftsService.addShift(shiftsDTO);
        return ResponseEntity.ok("The Shift is Added");
    }

    @GetMapping("/get/{shiftId}")
    public ResponseEntity<Shifts> getShift(@PathVariable UUID shiftId) {
        Shifts shifts = shiftsService.getShiftById(shiftId);
        if (shifts != null) {
            return ResponseEntity.ok(shifts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Shifts>> getAllShifts() {
        List<Shifts> shiftsList = shiftsService.getAllShifts();
        return ResponseEntity.ok(shiftsList);
    }
}
