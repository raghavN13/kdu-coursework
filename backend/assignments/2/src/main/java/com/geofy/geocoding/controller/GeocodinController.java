package com.geofy.geocoding.controller;
import com.geofy.geocoding.dto.geocoding.GeocodingResponse;
import com.geofy.geocoding.services.Geocoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geocoding")
public class GeocodinController {
    @Autowired
    private Geocoding geocodingobj;

    @GetMapping
    public GeocodingResponse forwardGeocoding(@RequestParam("address") String address) {
        return geocodingobj.geocodeAddress(address);
    }
}
