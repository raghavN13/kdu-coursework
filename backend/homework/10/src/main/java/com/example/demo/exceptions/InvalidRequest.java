package com.example.demo.exceptions;

public class InvalidRequest extends RuntimeException{
    public InvalidRequest(String message){
        super(message);
    }
}
