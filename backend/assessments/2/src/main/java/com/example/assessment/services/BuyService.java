package com.example.assessment.services;

import com.example.assessment.entities.Products;
import com.example.assessment.repositories.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyService {

    private ProductsRepo productsRepo;
    @Autowired
    public BuyService(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

}
