package com.example.demo2.utility;

import lombok.Data;

/**
 * Gives the description of the Speaker class
 */
@Data
public class Speaker {

    private final String brand;
    private double price;

    public Speaker() {
        this.brand = "Default Brand";
        this.price = 0.0;
    }

    public Speaker(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public void setPrice(double newSpeakerPrice) {
        this.price = newSpeakerPrice;
    }
}
