package com.example.assessment.services;
import com.example.assessment.entities.ShoppingCart;
import com.example.assessment.repositories.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ShoppingCartService {

    private final ShoppingCartRepo shoppingCartRepo;
    @Autowired

    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    public void addProduct(ShoppingCart shoppingCart){
        shoppingCartRepo.save(shoppingCart);
    }

    public void deleteProduct(UUID shoppingCartId){
        shoppingCartRepo.deleteById(shoppingCartId);
    }
}
