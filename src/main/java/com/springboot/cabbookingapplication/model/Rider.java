package com.springboot.cabbookingapplication.model;

public class Rider extends User {

    public Rider(Integer id, String name, Gender gender, Integer Age, Location location) {
        super(id, name, gender, Age, location);
    }
}
