package com.example.assessment.repositories;

import com.example.assessment.entities.Addresses;
import com.example.assessment.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AdressesRepo extends JpaRepository<Addresses,String> {
}