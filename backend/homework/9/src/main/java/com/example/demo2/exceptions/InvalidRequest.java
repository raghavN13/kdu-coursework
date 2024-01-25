package com.example.demo2.exceptions;

public class InvalidRequest extends RuntimeException{
    public InvalidRequest(String message){
        super(message);
    }
}
