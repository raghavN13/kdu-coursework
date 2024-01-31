package com.example.demo.services;
import com.example.demo.dto.ShiftsDTO;
import com.example.demo.entities.Shifts;
import com.example.demo.repository.ShiftsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftsService {

    private final ShiftsRepo shiftsRepo;

    @Autowired
    public ShiftsService(ShiftsRepo shiftsRepo) {
        this.shiftsRepo = shiftsRepo;
    }

    public void addShift(ShiftsDTO shiftsDTO) {
        shiftsRepo.addShift(shiftsDTO);
    }

    public Shifts getShiftById(UUID shiftId) {
        return shiftsRepo.getShiftById(shiftId);
    }

    public List<Shifts> getAllShifts() {
        return shiftsRepo.getAllShifts();
    }
}
