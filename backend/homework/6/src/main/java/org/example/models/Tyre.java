package org.example.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Getters and Setters for Tyres
 */
@Component
public class Tyre {

    private final String brand;
    private final double price;

    @Autowired
    public Tyre(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}