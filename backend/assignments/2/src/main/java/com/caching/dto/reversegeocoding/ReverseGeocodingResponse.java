package com.caching.dto.reversegeocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Gives the Structure of the response of ReverseGeocoding
 */
@Data
public class ReverseGeocodingResponse {
    private String address;

    /**
     * maps the response provided by the third party API to the response DTO
     * @param features an array that is the part of the response provided by the thirdparty API
     */
    @JsonProperty("features")
    private void unpackAddress(List<Feature> features) {
        if (features != null && !features.isEmpty()) {
            Feature feature = features.get(0);
            if (feature != null && feature.properties != null) {
                this.address = feature.properties.getAddress();
            }
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Feature {
        private Properties properties;
    }
}
