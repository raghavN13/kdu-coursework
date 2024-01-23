package org.example.main;

import org.example.models.Vehicle;
import org.example.mylogger.Log;
import org.example.services.InventoryStore;
import org.example.services.VehicleService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class Main {

    private final ObjectFactory<VehicleService> vehicleServiceFactory1Factory;
    private final ObjectFactory<VehicleService> vehicleServiceFactory2Factory;
    private final ObjectFactory<InventoryStore> inventoryStoreFactory;

    private VehicleService vehicleServiceFactory1;
    private VehicleService vehicleServiceFactory2;
    private InventoryStore inventoryStore;

    @Autowired
    public Main(@Qualifier("factory1") ObjectFactory<VehicleService> vehicleServiceFactory1Factory,
                @Qualifier("factory2") ObjectFactory<VehicleService> vehicleServiceFactory2Factory,
                ObjectFactory<InventoryStore> inventoryStoreFactory) {
        this.vehicleServiceFactory1Factory = vehicleServiceFactory1Factory;
        this.vehicleServiceFactory2Factory = vehicleServiceFactory2Factory;
        this.inventoryStoreFactory = inventoryStoreFactory;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example.services", "org.example.main");

        Main main = context.getBean(Main.class);

        main.run();

        context.close();
    }

    public void run() {
        vehicleServiceFactory1 = vehicleServiceFactory1Factory.getObject();
        vehicleServiceFactory2 = vehicleServiceFactory2Factory.getObject();
        inventoryStore = inventoryStoreFactory.getObject();

        vehicleServiceFactory1.init();
        vehicleServiceFactory2.init();

        // Print details of the most expensive vehicle from each factory
        Vehicle mostExpensiveVehicleFactory1 = vehicleServiceFactory1.findMostExpensiveVehicle();
        Vehicle mostExpensiveVehicleFactory2 = vehicleServiceFactory2.findMostExpensiveVehicle();

        Log.customLogger("Most Expensive Vehicle From Factory 1: " + mostExpensiveVehicleFactory1, "INFO");
        Log.customLogger("Most Expensive Vehicle From Factory 2: " + mostExpensiveVehicleFactory2, "INFO");

        // Print details of the highest and lowest price vehicles in the entire inventory
        Vehicle highestPriceVehicle = findHighestPriceVehicle();
        Vehicle lowestPriceVehicle = findLowestPriceVehicle();

        Log.customLogger("Highest Price Vehicle: " + highestPriceVehicle, "INFO");
        Log.customLogger("Lowest Price Vehicle: " + lowestPriceVehicle, "INFO");
    }

    private Vehicle findHighestPriceVehicle() {
        return inventoryStore.getVehicles().stream()
                .max(Comparator.comparingDouble(Vehicle::calculatePrice))
                .orElse(null);
    }

    private Vehicle findLowestPriceVehicle() {
        return inventoryStore.getVehicles().stream()
                .min(Comparator.comparingDouble(Vehicle::calculatePrice))
                .orElse(null);
    }
}
