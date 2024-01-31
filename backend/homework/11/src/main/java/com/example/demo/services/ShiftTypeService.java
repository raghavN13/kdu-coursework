package com.example.demo.services;

import com.example.demo.dto.ShiftTypeDTO;
import com.example.demo.entities.ShiftType;
import com.example.demo.repository.ShiftTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftTypeService {

    private final ShiftTypeRepo shiftTypeRepo;

    @Autowired
    public ShiftTypeService(ShiftTypeRepo shiftTypeRepo) {
        this.shiftTypeRepo = shiftTypeRepo;
    }

    public void addShiftType(ShiftTypeDTO shiftTypeDTO) {
        shiftTypeRepo.addShiftType(shiftTypeDTO);
    }

    public ShiftType getShiftTypeById(UUID shiftTypeId) {
        return shiftTypeRepo.getShiftTypeById(shiftTypeId);
    }

    public List<ShiftType> getAllShiftTypes() {
        return shiftTypeRepo.getAllShiftTypes();
    }
}
