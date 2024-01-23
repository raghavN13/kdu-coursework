package org.example.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Getters and Setters for Speakers
 */
@Component
public class Speaker {

    private final String brand;
    private  double price;

    @Autowired
    public Speaker(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    public void setPrice(double newSpeakerPrice) {
        this.price = newSpeakerPrice;
    }
}
