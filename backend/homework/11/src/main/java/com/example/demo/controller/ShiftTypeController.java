package com.example.demo.controller;
import com.example.demo.dto.ShiftTypeDTO;
import com.example.demo.entities.ShiftType;
import com.example.demo.services.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shift-type")
public class ShiftTypeController {

    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDTO shiftTypeDTO) {
        shiftTypeService.addShiftType(shiftTypeDTO);
        return ResponseEntity.ok("The ShiftType is Added");
    }

    @GetMapping("/get/{shiftTypeId}")
    public ResponseEntity<ShiftType> getShiftType(@PathVariable UUID shiftTypeId) {
        ShiftType shiftType = shiftTypeService.getShiftTypeById(shiftTypeId);
        if (shiftType != null) {
            return ResponseEntity.ok(shiftType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShiftType>> getAllShiftTypes() {
        List<ShiftType> shiftTypes = shiftTypeService.getAllShiftTypes();
        return ResponseEntity.ok(shiftTypes);
    }
}
