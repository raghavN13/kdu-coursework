package com.example.assessment.entities;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "shopping_cart")

public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cartId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne
    @JoinColumn(name="product_id")
    private Products product;

}
