package org.example.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class gives the information about the type of vehicles
 */
@Component
public class Vehicle {

    private final Tyre tyre;
    private final Speaker speaker;

    @Autowired
    public Vehicle(Tyre tyre, Speaker speaker) {
        this.tyre = tyre;
        this.speaker = speaker;
    }

    public double calculatePrice() {
        return tyre.getPrice() + speaker.getPrice();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "tyre=" + tyre +
                ", speaker=" + speaker +
                ", price=" + calculatePrice() +
                '}';
    }
}

