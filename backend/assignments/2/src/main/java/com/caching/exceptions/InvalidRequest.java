package com.caching.exceptions;

/**
 * Handles the invalid request that is made to the third porty API
 */
public class InvalidRequest extends RuntimeException{
    public InvalidRequest(String message){
        super(message);
    }
}
