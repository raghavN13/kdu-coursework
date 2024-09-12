package com.example.demo.controller;

import com.example.demo.dto.TenantDTO;
import com.example.demo.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    TenantService tenantService;
    @Autowired
    public TenantController(TenantService tenantService){
        this.tenantService = tenantService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenantDTO){
        tenantService.addTenant(tenantDTO);
        return ResponseEntity.ok("The User is Added");
    }


}
