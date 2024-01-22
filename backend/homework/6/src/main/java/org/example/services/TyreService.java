package org.example.services;

import org.example.models.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Gives the Information about the tyres such as its Brand and Price
 */

@Component
class TyreService {

    @Bean
    public Tyre generateTyre() {
        String brand = getRandomBrand();
        double price = generateRandomPrice();
        return new Tyre(brand, price);
    }

    private String getRandomBrand() {
        String[] brands = {"MRF", "Bridgestone"};
        return brands[new Random().nextInt(brands.length)];
    }

    private double generateRandomPrice() {
        return new Random().nextDouble() * 100 + 100; // Random price between 100 and 200
    }
}
