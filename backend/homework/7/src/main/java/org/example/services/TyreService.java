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

    private static final Random random = new Random();
    private TyreService(){

    }

    @Bean
    public static Tyre generateTyre() {
        String brand = getRandomBrand();
        double price = generateRandomPrice();
        return new Tyre(brand, price);
    }

    private static String getRandomBrand() {
        String[] brands = {"MRF", "Bridgestone"};
        return brands[random.nextInt(brands.length)];
    }

    private static double generateRandomPrice() {
        return random.nextDouble() * 100 + 100; // Random price between 100 and 200
    }
}
