package com.caching.services;
import com.caching.Config.ApiConfig;
import com.caching.dto.reversegeocoding.ReverseGeocodingResponse;
import com.caching.exceptions.InvalidRequest;
import com.caching.validators.ReverseGeocodingRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.caching.constants.OtherConstants.INVALID_REQUEST_CONSTANT;
import static com.caching.constants.OtherConstants.REVERSE_GEOCODE_URL;

@Service
public class ReverseGeocoding {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReverseGeocoding.class);
    private final ApiConfig apiConfig;
    public ReverseGeocoding(ApiConfig apiConfig){
        this.apiConfig = apiConfig;
    }

    private String buildReverseGeocodingApiUrl(Double latitude, Double longitude) {
        String apiKey = apiConfig.getApiKey();
        String reverseApiUrl = REVERSE_GEOCODE_URL;
        return String.format("%s?lat=%s&lon=%s&apiKey=%s",
                reverseApiUrl, latitude, longitude, apiKey);
    }
    @Cacheable(value = "reverse-geocoding", key = "{#latitude, #longitude}")
    public ReverseGeocodingResponse reverseGeocode(double latitude , double longitude) {
        ReverseGeocodingRequestValidator validator = new ReverseGeocodingRequestValidator();
        if(!validator.check(latitude, longitude)){
            throw new InvalidRequest(INVALID_REQUEST_CONSTANT);
        }
        LOGGER.info("Reverse Geocoding The Latitude and Longitude");
        String apiUrl = buildReverseGeocodingApiUrl(latitude, longitude);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, ReverseGeocodingResponse.class);
    }

}
