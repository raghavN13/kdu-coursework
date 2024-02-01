package com.example.springjpa.services;

import com.example.springjpa.entities.ShiftType;
import com.example.springjpa.repository.ShiftTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShiftTypeService {

    private final ShiftTypeRepo shiftTypeRepo;

    @Autowired
    public ShiftTypeService(ShiftTypeRepo shiftTypeRepo) {
        this.shiftTypeRepo = shiftTypeRepo;
    }

    public void addShiftType(ShiftType shiftType) {
        shiftTypeRepo.save(shiftType);
    }

}
