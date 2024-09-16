package com.example.assessment.controllers;

import com.example.assessment.entities.ShoppingCart;
import com.example.assessment.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class AddToCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AddToCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/addtocart")
    public ResponseEntity<String> addToCart(@RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.addProduct(shoppingCart);
        return ResponseEntity.ok("The Product is Added");
    }

    @DeleteMapping("/delete/{productid}")
    public ResponseEntity<String> deleteFromCart(@PathVariable UUID shoppingCartId) {
        shoppingCartService.deleteProduct(shoppingCartId);
        return ResponseEntity.ok("The ShiftUser is Deleted");
    }


}
