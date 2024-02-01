package com.example.springjpa.repository;

import com.example.springjpa.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TenantRepo extends JpaRepository<Tenant,UUID> {

}
