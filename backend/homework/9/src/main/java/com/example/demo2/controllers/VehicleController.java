package com.example.demo2.controllers;

import com.example.demo2.dto.VehicleDTO;
import com.example.demo2.utility.Vehicle;
import com.example.demo2.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
/**
 * This is the implementation of the APIs
 */


public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        List<Vehicle>vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }
    @PostMapping
    public ResponseEntity<Vehicle>createVehicle(@RequestBody VehicleDTO vehicleDTO){
        Vehicle createdVehicle = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.ok(createdVehicle);
    }
    @PutMapping("/{name}")
    public ResponseEntity<Vehicle>updateVehicle(@PathVariable String name , @RequestBody VehicleDTO vehicleDTO){
        Vehicle updatedVehicle = vehicleService.updateVehicle(name,vehicleDTO);
        return ResponseEntity.ok(updatedVehicle);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String name) {
        vehicleService.deleteVehicle(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/most-expensive")
    public ResponseEntity<Vehicle> getMostExpensiveVehicle() {
        Optional<Vehicle> mostExpensiveVehicle = vehicleService.findMostExpensive();
        return mostExpensiveVehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/least-expensive")
    public ResponseEntity<Vehicle> getLeastExpensiveVehicle() {
        Optional<Vehicle> leastExpensiveVehicle = vehicleService.findLeastExpensive();
        return leastExpensiveVehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
