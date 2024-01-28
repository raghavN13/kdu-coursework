package com.geofy.geocoding.controller;
import com.geofy.geocoding.dto.reversegeocoding.ReverseGeocodingResponse;
import com.geofy.geocoding.services.ReverseGeocoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
