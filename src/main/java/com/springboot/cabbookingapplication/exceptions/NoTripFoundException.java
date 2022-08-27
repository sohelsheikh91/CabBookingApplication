package com.springboot.cabbookingapplication.exceptions;

public class NoTripFoundException extends RuntimeException{
    public NoTripFoundException(String message) {
        super(message);
    }
}
