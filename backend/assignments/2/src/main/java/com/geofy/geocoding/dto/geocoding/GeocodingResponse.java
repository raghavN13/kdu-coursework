package com.geofy.geocoding.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GeocodingResponse {
    private Double latitude;
    private Double longitude;

    @JsonProperty("features")
    private void unpackCoordinates(List<Feature> features) {
        if (features != null && !features.isEmpty()) {
            Feature feature = features.get(0);
            if (feature != null && feature.geometry != null && feature.geometry.coordinates != null && feature.geometry.coordinates.size() == 2) {
                this.latitude = feature.geometry.coordinates.get(1);
                this.longitude = feature.geometry.coordinates.get(0);
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Feature {
        private Geometry geometry;
    }
}
