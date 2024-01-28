package com.caching.validators;
public class GeocodingRequestValidator {
    /**
     * Checks Whether the Address is null or not
     * @param address
     * @return Boolean data type
     */
    public boolean checkForValidation(String address){
        return address != null;
    }
}
