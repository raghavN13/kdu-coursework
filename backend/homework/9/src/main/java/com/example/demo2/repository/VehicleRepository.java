package com.example.demo2.repository;
import com.example.demo2.dto.VehicleDTO;
import com.example.demo2.utility.Speaker;
import com.example.demo2.utility.Tyre;
import com.example.demo2.utility.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the repository meant to interact with the database but in this holding the data in a list
 */

@Repository
public class VehicleRepository {
    private static List<Vehicle>vehicleList  =  new ArrayList<>();
    private static int id = 1;

    public List<Vehicle> getAllVehicle(){
        return vehicleList;
    }
    public boolean isEmpty(){
        return (vehicleList.isEmpty());
    }

    public Vehicle createVehicle(VehicleDTO vehicleDTO){
        Speaker speaker  = new Speaker(vehicleDTO.getSpeakerBrand(),vehicleDTO.getSpeakerPrice());
        Tyre tyre = new Tyre(vehicleDTO.getTyreBrand(), vehicleDTO.getTyrePrice());
        Vehicle vehicle = new Vehicle(tyre,speaker);
        vehicle.setId(id++);
        double vehiclePrice = vehicleDTO.getSpeakerPrice()+vehicleDTO.getTyrePrice();
        vehicle.setVehiclePrice(vehiclePrice);
        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicleList.add(vehicle);
        return vehicle;
    }

    public Vehicle updateVehicle(String name , VehicleDTO vehicleDTO){
        // check if the vehicle is present inside the list or not
        boolean present = false;
        Vehicle vehicle = null;
        for(int i=0 ;i<vehicleList.size() ;i++){
            if(vehicleList.get(i).getVehicleName().equals(name)){
                present = true;
                vehicle = vehicleList.get(i);
            }
        }
        if(present){
            Tyre tyreOne = new Tyre(vehicleDTO.getTyreBrand(), vehicleDTO.getTyrePrice());
            Speaker speakerOne = new Speaker(vehicleDTO.getSpeakerBrand(), vehicleDTO.getSpeakerPrice());
            vehicle.setTyre(tyreOne);
            vehicle.setSpeaker(speakerOne);
            double vehiclePrice = vehicleDTO.getSpeakerPrice()+vehicleDTO.getTyrePrice();
            vehicle.setVehiclePrice(vehiclePrice);
            vehicle.setVehicleName(vehicleDTO.getVehicleName());
            return vehicle;

        }
        else{
            Tyre tyre = new Tyre(vehicleDTO.getTyreBrand(), vehicleDTO.getTyrePrice());
            Speaker speaker = new Speaker(vehicleDTO.getSpeakerBrand(), vehicleDTO.getSpeakerPrice());
            Vehicle newVehicle = new Vehicle(tyre, speaker);
            // setting the provided id for the new vehicle
            newVehicle.setId(id);
            double vehiclePrice = vehicleDTO.getSpeakerPrice()+vehicleDTO.getTyrePrice();
            newVehicle.setVehiclePrice(vehiclePrice);
            newVehicle.setVehicleName(vehicleDTO.getVehicleName());


            vehicleList.add(newVehicle);
            return newVehicle;
        }
    }

    public void deleteVehicle(String name){
        vehicleList.removeIf(vehicle -> vehicle.getVehicleName().equals(name));
    }

}
