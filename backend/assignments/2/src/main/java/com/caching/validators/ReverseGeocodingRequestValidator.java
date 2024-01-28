package com.caching.validators;
public class ReverseGeocodingRequestValidator {
    /**
     * Checks whether the latitude or the Longitude are invalid or not
     * @param latitude
     * @param longitude
     * @return Boolean Datatype
     */

    public boolean check(double latitude , double longitude){
        return latitude != 0 && longitude != 0;
    }
}
