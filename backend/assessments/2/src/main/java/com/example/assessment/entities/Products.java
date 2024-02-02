package com.example.assessment.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;
    private String name;
    private String description;
    private double price;
    @Column(name = "stock_quantity")
    private double stockQuantity;
}
