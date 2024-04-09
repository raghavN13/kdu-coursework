package com.example.springjpa.services;
import com.example.springjpa.entities.Shifts;
import com.example.springjpa.repository.ShiftsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftsService {

    private final ShiftsRepo shiftsRepo;

    @Autowired
    public ShiftsService(ShiftsRepo shiftsRepo) {
        this.shiftsRepo = shiftsRepo;
    }

    public void addShift(Shifts shift) {
        shiftsRepo.save(shift);
    }
}
