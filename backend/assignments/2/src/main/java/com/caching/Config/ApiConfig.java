package com.caching.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * This class initializes the API key which remains constant throughout the application and can be used with the help of this class
 */
@Configuration
public class ApiConfig {


    @Value("${geoapify.apiKey}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}