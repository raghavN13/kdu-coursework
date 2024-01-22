package org.example.main;

import org.example.models.Vehicle;
import org.example.mylogger.Log;
import org.example.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example.services");

        VehicleService vehicleService = context.getBean(VehicleService.class);

        vehicleService.init();
        Vehicle mostExpensiveVehicle = vehicleService.findMostExpensiveVehicle();
        Log.customLogger("\nMost Expensive Vehicle: " + mostExpensiveVehicle,"INFO");

        context.close();
    }
}
