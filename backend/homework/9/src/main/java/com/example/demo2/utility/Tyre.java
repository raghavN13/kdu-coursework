package com.example.demo2.utility;

import lombok.Data;

/**
 * Gives the description of the tyre class
 */

@Data
public class Tyre {

    private String brand;
    private double price;

    public Tyre(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public void setPrice(double newTyrePrice) {
        this.price = newTyrePrice;
    }
}
