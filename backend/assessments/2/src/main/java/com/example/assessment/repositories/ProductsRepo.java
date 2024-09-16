package com.example.assessment.repositories;

import com.example.assessment.entities.Products;
import com.example.assessment.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductsRepo extends JpaRepository<Products, UUID> {
    @Query("UPDATE Products SET stockQuantity=?1 WHERE productId = ?2")
    Optional<Products> updateUser(int stockQuantity, UUID id);
}