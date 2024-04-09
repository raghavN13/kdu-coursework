package com.example.springjpa.controller;

import com.example.springjpa.entities.Tenant;
import com.example.springjpa.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    TenantService tenantService;
    @Autowired
    public TenantController(TenantService tenantService){
        this.tenantService = tenantService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant){
        tenantService.addTenant(tenant);
        return ResponseEntity.ok("The User is Added");
    }


}
