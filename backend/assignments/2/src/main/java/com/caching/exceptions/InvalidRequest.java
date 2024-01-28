package com.caching.exceptions;

public class InvalidRequest extends RuntimeException{
    public InvalidRequest(String message){
        super(message);
    }
}
