package com.caching.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * It is a class that is used in the mapping purpose for the GeocodingResponse class
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Geometry {
    public List<Double> coordinates;
}
