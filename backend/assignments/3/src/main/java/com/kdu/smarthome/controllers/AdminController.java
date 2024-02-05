package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.Users.AdminRequestDTO;
import com.kdu.smarthome.dto.Users.AdminResponseDTO;
import com.kdu.smarthome.services.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the Admin controller and is used to the hit the API endPoints to register the user
 */
@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AdminResponseDTO> registerUser(@RequestBody AdminRequestDTO adminRequestDTO){
        AdminResponseDTO adminResponseDTO = adminService.registerUser(adminRequestDTO);

        return new ResponseEntity<>(adminResponseDTO, HttpStatus.OK);
    }


}
