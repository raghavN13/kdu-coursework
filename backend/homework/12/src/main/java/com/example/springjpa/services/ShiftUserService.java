package com.example.springjpa.services;
import com.example.springjpa.entities.ShiftUser;
import com.example.springjpa.repository.ShiftUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftUserService {

    private final ShiftUserRepo shiftUserRepo;

    @Autowired
    public ShiftUserService(ShiftUserRepo shiftUserRepo) {
        this.shiftUserRepo = shiftUserRepo;
    }

    public void addShiftUser(ShiftUser shiftUser) {
        shiftUserRepo.save(shiftUser);
    }



}
