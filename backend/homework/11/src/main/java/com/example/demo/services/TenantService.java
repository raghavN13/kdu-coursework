package com.example.demo.services;

import com.example.demo.dto.TenantDTO;
import com.example.demo.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {
    TenantRepo tenantRepo;
    @Autowired
    public TenantService(TenantRepo tenantRepo){
        this.tenantRepo =  tenantRepo;
    }

    public void addTenant(TenantDTO tenantDTO){
        tenantRepo.addTenant(tenantDTO);
    }
}
