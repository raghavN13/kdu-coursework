package com.geofy.geocoding.validators;
public class ReverseGeocodingRequestValidator {

    public boolean check(double latitude , double longitude){
        return latitude != 0 && longitude != 0;
    }
}
