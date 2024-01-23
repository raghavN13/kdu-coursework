package org.example.services;

import org.example.models.Speaker;
import org.example.models.Tyre;
import org.example.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Gives the inforamtion about the Vehicles generated by Factory one
 */

@Component
@Qualifier("factory1")
public class VehicleServiceFactoryOne extends VehicleService {

    @Autowired
    public VehicleServiceFactoryOne(TyreService tyreService, SpeakerService speakerService, InventoryStore inventoryStore) {
        super(tyreService, speakerService, inventoryStore);
        // Adjust pricing structure specific to Factory 1
    }

    @Override
    public List<Vehicle> generateVehicles(int count) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Tyre tyre = TyreService.generateTyre();
            Speaker speaker = SpeakerService.generateSpeaker();

            // Apply pricing adjustments specific to Factory 1
            adjustPricingForFactory1(tyre);

            Vehicle vehicle = new Vehicle(tyre, speaker);
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    private void adjustPricingForFactory1(Tyre tyre) {
        // Implement pricing adjustments specific to Factory 1
        // For example, you can increase the price of the tyre by 10%
        double newTyrePrice = tyre.getPrice() * 1.1;
        tyre.setPrice(newTyrePrice);
    }
}
