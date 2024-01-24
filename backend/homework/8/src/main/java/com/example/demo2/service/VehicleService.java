package com.example.demo2.service;

import com.example.demo2.dto.VehicleDTO;
import com.example.demo2.utility.Speaker;
import com.example.demo2.utility.Tyre;
import com.example.demo2.utility.Vehicle;
import com.example.demo2.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Providing Abstraction and implementing the methods called by the controller
 */

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;



    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Gives the List of the Vehicles
     * @return
     */
    public List<Vehicle> getVehicles() {
        return  vehicleRepository.getAllVehicle();

    }

    /**
     * Delets the vehicle from the list
     * @param vehicleName
     */

    public void deleteVehicle(String vehicleName) {
        vehicleRepository.deleteVehicle(vehicleName);
    }

    /**
     * creates the vehicle in the list using the necessary data
     * @param vehicleDTO
     * @return
     */
    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        return vehicleRepository.createVehicle(vehicleDTO);
    }

    /**
     * updates the vehicle based on the name of the vehicle
     * @param name
     * @param vehicleDTO
     * @return
     */
    public Vehicle updateVehicle(String name , VehicleDTO vehicleDTO) {
        return vehicleRepository.updateVehicle(name,vehicleDTO);
    }

    /**
     * finds the most expensive vehile present in the list
     * @return
     */
    public Optional<Vehicle> findMostExpensive() {
        return vehicleRepository.getAllVehicle().stream().max(Comparator.comparingDouble(Vehicle::getVehiclePrice));
    }

    /**
     * Finds the least expensive vehicle present in the list
     * @return
     */
    public Optional<Vehicle> findLeastExpensive() {
        return vehicleRepository.getAllVehicle().stream().min(Comparator.comparingDouble(Vehicle::getVehiclePrice));
    }
    public Vehicle convertDTOToEntity(VehicleDTO vehicleDTO) {
        Tyre tyre = new Tyre(vehicleDTO.getTyreBrand(),vehicleDTO.getTyrePrice());
        Speaker speaker = new Speaker(vehicleDTO.getSpeakerBrand(), vehicleDTO.getSpeakerPrice());
        return  new Vehicle(tyre,speaker);
    }

}
