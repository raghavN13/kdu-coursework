package com.example.springjpa.services;

import com.example.springjpa.entities.Tenant;
import com.example.springjpa.repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {
    TenantRepo tenantRepo;
    @Autowired
    public TenantService(TenantRepo tenantRepo){
        this.tenantRepo =  tenantRepo;
    }

    public void addTenant(Tenant tenant){
        tenantRepo.save(tenant);
    }
}
