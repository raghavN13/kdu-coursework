package com.kdu.smarthome.mappers;

import com.kdu.smarthome.dto.Users.AdminRequestDTO;
import com.kdu.smarthome.entities.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Maps the DTO to the object
 */
@Component
public class AdminMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admins mapUser(AdminRequestDTO adminRequestDTO){
        Admins admin = new Admins();
        admin.setUsername(adminRequestDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(adminRequestDTO.getPassword()));
        admin.setFirstName(adminRequestDTO.getFirstName());
        admin.setLastName(adminRequestDTO.getLastName());
        admin.setEmail(adminRequestDTO.getEmail());
        admin.setName(admin.getName());


        return admin;
    }
}
