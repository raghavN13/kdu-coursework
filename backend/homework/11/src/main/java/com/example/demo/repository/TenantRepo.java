package com.example.demo.repository;

import com.example.demo.dto.TenantDTO;
import com.example.demo.entities.Tenant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TenantRepo {

    JdbcTemplate jdbcTemplate;

    public TenantRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTenant(TenantDTO tenantDTO){
        String sql = "INSERT INTO tenant (tenant_name) VALUES (?)";
        jdbcTemplate.update(sql,tenantDTO.getName());
    }

}
