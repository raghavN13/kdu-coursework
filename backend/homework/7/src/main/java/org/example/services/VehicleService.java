package org.example.services;

import org.example.models.Speaker;
import org.example.models.Tyre;
import org.example.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@Scope("prototype")
public class VehicleService {

     final TyreService tyreService;
     final SpeakerService speakerService;
    private final InventoryStore inventoryStore;
    private List<Vehicle> vehicles;

    @Autowired
    public VehicleService(TyreService tyreService, SpeakerService speakerService, InventoryStore inventoryStore) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
        this.inventoryStore = inventoryStore;
    }

    @PostConstruct
    public void init() {
        vehicles = generateVehicles(5);
        inventoryStore.setVehicles(vehicles);
    }

    /**
     * generates the list of the vehicles
     * @return the list of the vehicles
     */
    public List<Vehicle> generateVehicles(int count) {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Tyre tyre = TyreService.generateTyre();
            Speaker speaker = SpeakerService.generateSpeaker();
            Vehicle vehicle = new Vehicle(tyre, speaker);
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public Vehicle findMostExpensiveVehicle() {
        if (vehicles == null || vehicles.isEmpty()) {
            return null;
        }

        return vehicles.stream()
                .max(Comparator.comparingDouble(Vehicle::calculatePrice))
                .orElse(null);
    }

    // Add any additional methods or logic as needed

}
