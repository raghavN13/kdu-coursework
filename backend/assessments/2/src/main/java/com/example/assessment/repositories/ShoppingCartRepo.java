package com.example.assessment.repositories;

import com.example.assessment.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, UUID> {
}
