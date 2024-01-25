package com.example.demo2.service;

import com.example.demo2.dto.VehicleDTO;
import com.example.demo2.exceptions.InvalidRequest;
import com.example.demo2.exceptions.ResourceNotFound;
import com.example.demo2.utility.Vehicle;
import com.example.demo2.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.example.demo2.constants.Constants.EMPTY;
import static com.example.demo2.constants.Constants.INVALID;

/**
 * Providing Abstraction and implementing the methods called by the controller
 */

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);


    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Gives the List of the Vehicles
     *
     * @return
     */

    public boolean checkForValidRequest(VehicleDTO vehicleDTO) {
        return (!(vehicleDTO == null || vehicleDTO.getTyrePrice() == 0 || vehicleDTO.getVehicleName() == null || vehicleDTO.getTyreBrand() == null || vehicleDTO.getSpeakerBrand() == null || vehicleDTO.getSpeakerPrice() == 0));

    }

    public List<Vehicle> getVehicles() {
        if (vehicleRepository.isEmpty()) {
            LOGGER.info(EMPTY);
            throw new ResourceNotFound(EMPTY);
        } else {
            return vehicleRepository.getAllVehicle();

        }
    }

    /**
     * Delets the vehicle from the list
     *
     * @param vehicleName
     */

    public void deleteVehicle(String vehicleName) {
        LOGGER.info("Deleting vehicle: {}", vehicleName);
        boolean vehicleFound = vehicleRepository.getAllVehicle().removeIf(vehicle -> vehicle.getVehicleName().equals(vehicleName));

        if (vehicleFound) {
            LOGGER.info("The Vehicle '{}' has been deleted", vehicleName);
        } else {
            LOGGER.error("Vehicle '{}' not found. Unable to delete.", vehicleName);
            throw new ResourceNotFound("Vehicle not found with name: " + vehicleName);
        }

    }

    /**
     * creates the vehicle in the list using the necessary data
     *
     * @param vehicleDTO
     * @return
     */
    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        if (!checkForValidRequest(vehicleDTO)) {
            LOGGER.info(INVALID);
            throw new InvalidRequest(INVALID);
        }
        LOGGER.info("The vehicle has been successfully added");
        return vehicleRepository.createVehicle(vehicleDTO);
    }

    /**
     * updates the vehicle based on the name of the vehicle
     *
     * @param name
     * @param vehicleDTO
     * @return
     */
    public Vehicle updateVehicle(String name, VehicleDTO vehicleDTO) {
        if (!checkForValidRequest(vehicleDTO)) {
            LOGGER.info(INVALID);
            throw new InvalidRequest(INVALID);
        }
        LOGGER.info("The vehicle has been successfully updated");
        return vehicleRepository.updateVehicle(name, vehicleDTO);
    }

    /**
     * finds the most expensive vehicle present in the list
     *
     * @return
     */
    public Optional<Vehicle> findMostExpensive() {
        if (vehicleRepository.isEmpty()) {
            LOGGER.info(EMPTY);
            throw new ResourceNotFound(EMPTY);
        }
        return vehicleRepository.getAllVehicle().stream().max(Comparator.comparingDouble(Vehicle::getVehiclePrice));
    }

    /**
     * Finds the least expensive vehicle present in the list
     *
     * @return
     */
    public Optional<Vehicle> findLeastExpensive() {
        if (vehicleRepository.isEmpty()) {
            LOGGER.info(EMPTY);
            throw new ResourceNotFound(EMPTY);
        }
        return vehicleRepository.getAllVehicle().stream().min(Comparator.comparingDouble(Vehicle::getVehiclePrice));
    }

}
