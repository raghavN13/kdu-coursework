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

/**
 * The pupose of this class is to define the objectives in the process of Geocoding
 */

@Service
public class Geocoding {
    private static final Logger LOGGER = LoggerFactory.getLogger(Geocoding.class);
    private final ApiConfig apiConfig;
    @Autowired
    public Geocoding(ApiConfig apiConfig){
        this.apiConfig = apiConfig;
    }

    /**
     * Buils the URL used for fetching the data from the third party API
     * @param address in the form of the string
     * @return String that is the URL
     */
    private String buildGeocodingApiUrl(String address) {
        String apiKey = apiConfig.getApiKey();
        String geoCodingApiUrl = GEO_CODE_URL;
        return String.format("%s?text=%s&apiKey=%s", geoCodingApiUrl, address, apiKey);
    }

    /**
     * Returns a RestTemplate object that is used by the controller to communicate with the third party API
     * checks for the validity of the params and throws the necessary exceptions upon checking the validity
     * @param address
     * @return Instance of the class RestTemplate
     */
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
