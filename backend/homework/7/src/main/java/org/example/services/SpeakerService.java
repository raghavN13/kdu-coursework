package org.example.services;

import org.example.models.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Gives the information about the speakers such as the brand and the price
 */
@Component
class SpeakerService {
    private SpeakerService(){}

    private static final Random random = new Random();

    @Bean
    public static Speaker generateSpeaker() {
        String brand = getRandomBrand();
        double price = generateRandomPrice();
        return new Speaker(brand, price);
    }

    private static String getRandomBrand() {
        String[] brands = {"Sony", "Bose"};
        return brands[random.nextInt(brands.length)];
    }

    private static double generateRandomPrice() {
        return random.nextDouble() * 50 + 50; // Random price between 50 and 100
    }
}
