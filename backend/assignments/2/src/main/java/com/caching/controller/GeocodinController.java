package com.caching.controller;

import com.caching.dto.geocoding.GeocodingResponse;
import com.caching.services.Geocoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
