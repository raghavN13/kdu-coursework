package com.caching.controller;

import com.caching.dto.reversegeocoding.ReverseGeocodingResponse;
import com.caching.services.ReverseGeocoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reverse-geocoding")
public class ReverseGeocodingController {
    @Autowired
    private ReverseGeocoding reverseGeocoding;

    @GetMapping
    public ReverseGeocodingResponse reverseGeocoding(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
        return reverseGeocoding.reverseGeocode(latitude,longitude);
    }
}
