package org.example.services;

import org.example.models.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Inventory Store Class gives the structure of the inventory of the vehicles
 */

@Component
@Scope("singleton")
public class InventoryStore {

    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
