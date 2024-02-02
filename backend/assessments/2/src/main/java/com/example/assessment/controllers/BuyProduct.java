package com.example.assessment.controllers;

import com.example.assessment.entities.Products;
import com.example.assessment.services.BuyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/buy")

public class BuyProduct {
    private BuyService buyService;

    public BuyProduct(BuyService buyService) {
        this.buyService = buyService;
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<String> update(@PathVariable UUID productId , @RequestBody Products products){
       return ResponseEntity.ok("The product has been updated");

   }

}
