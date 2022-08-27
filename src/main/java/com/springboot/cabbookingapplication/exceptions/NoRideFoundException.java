package com.springboot.cabbookingapplication.exceptions;

public class NoRideFoundException extends RuntimeException{
    public NoRideFoundException(String message) {
        super(message);
    }
}
