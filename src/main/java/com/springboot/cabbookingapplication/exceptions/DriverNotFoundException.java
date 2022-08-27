package com.springboot.cabbookingapplication.exceptions;

public class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException(String message) {
        super(message);
    }
}
