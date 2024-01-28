package com.caching.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {


    @Value("${geoapify.apiKey}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}