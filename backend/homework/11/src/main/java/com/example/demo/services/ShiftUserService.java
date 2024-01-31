package com.example.demo.services;
import com.example.demo.dto.ShiftUserDTO;
import com.example.demo.entities.ShiftUser;
import com.example.demo.repository.ShiftUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftUserService {

    private final ShiftUserRepo shiftUserRepo;

    @Autowired
    public ShiftUserService(ShiftUserRepo shiftUserRepo) {
        this.shiftUserRepo = shiftUserRepo;
    }

    public void addShiftUser(ShiftUserDTO shiftUserDTO) {
        shiftUserRepo.addShiftUser(shiftUserDTO);
    }

    public ShiftUser getShiftUserById(UUID shiftUserId) {
        return shiftUserRepo.getShiftUserById(shiftUserId);
    }

    public List<ShiftUser> getAllShiftUsers() {
        return shiftUserRepo.getAllShiftUsers();
    }
}
