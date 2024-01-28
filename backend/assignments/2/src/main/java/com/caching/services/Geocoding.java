package com.caching.services;

import com.caching.Config.ApiConfig;
import com.caching.dto.geocoding.GeocodingResponse;
import com.caching.exceptions.InvalidRequest;
import com.caching.validators.GeocodingRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.caching.constants.OtherConstants.GEO_CODE_URL;
import static com.caching.constants.OtherConstants.INVALID_REQUEST_CONSTANT;

@Service
public class Geocoding {
    private static final Logger LOGGER = LoggerFactory.getLogger(Geocoding.class);
    private final ApiConfig apiConfig;
    @Autowired
    public Geocoding(ApiConfig apiConfig){
        this.apiConfig = apiConfig;
    }

    private String buildGeocodingApiUrl(String address) {
        String apiKey = apiConfig.getApiKey();
        String geoCodingApiUrl = GEO_CODE_URL;
        return String.format("%s?text=%s&apiKey=%s", geoCodingApiUrl, address, apiKey);
    }
    @Cacheable(value = "geocoding", key = "#address", unless = "#result == null or #address.toLowerCase().contains('goa')")
    public GeocodingResponse geocodeAddress(String address) {
        GeocodingRequestValidator validator =  new GeocodingRequestValidator();
        if(!validator.checkForValidation(address)){
            throw new InvalidRequest(INVALID_REQUEST_CONSTANT);
        }
        LOGGER.info("Geocoding The Address");
        String apiUrl = buildGeocodingApiUrl(address);
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForObject(apiUrl, GeocodingResponse.class);


    }
}
