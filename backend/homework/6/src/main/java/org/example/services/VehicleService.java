package org.example.services;
import org.example.models.Speaker;
import org.example.models.Tyre;
import org.example.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * this class invokes the vehicleService method initializes the list and gets the data from the models package about the speakers and tyres
 */

@Component
public class VehicleService {

    private final TyreService tyreService;
    private final SpeakerService speakerService;

    @Autowired
    public VehicleService(TyreService tyreService, SpeakerService speakerService) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
    }

    private List<Vehicle> vehicles;

    @PostConstruct
    public void init() {
        // Initialization logic, e.g., generate vehicles
        vehicles = generateVehicles(5);

        // Print details of the most expensive vehicle on startup

    }

    public List<Vehicle> generateVehicles(int count) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Tyre tyre = tyreService.generateTyre();
            Speaker speaker = speakerService.generateSpeaker();
            Vehicle vehicle = new Vehicle(tyre, speaker);
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    /**
     * implements the function to find the most expensive vehicle
     * @return returns a vehicle type object
     */
    public Vehicle findMostExpensiveVehicle() {
        if (vehicles == null || vehicles.isEmpty()) {
            return null;
        }

        return vehicles.stream()
                .max(Comparator.comparingDouble(Vehicle::calculatePrice))
                .orElse(null);
    }
}
