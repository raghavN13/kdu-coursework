package com.example.demo2.utility;

import lombok.Data;

/**
 * Gives the description of the Vehicle Class
 */

@Data
public class Vehicle {

    private Tyre tyre;
    private Speaker speaker;
    private int id;
    private double vehiclePrice;
    private String vehicleName;

    public Vehicle(Tyre tyre, Speaker speaker) {
        this.tyre = tyre;
        this.speaker = speaker;
    }

    public double calculatePrice() {
        return tyre.getPrice() + speaker.getPrice();
    }
}
