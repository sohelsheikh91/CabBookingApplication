package com.springboot.cabbookingapplication.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver extends User{

    private Vehicle car;
    private Boolean isAvailable;

    public Driver(Integer id, String name, Gender gender, Integer Age, Location location, Vehicle car, Boolean isAvailable) {

        super(id, name, gender, Age, location);
        this.car = car;
        this.isAvailable = isAvailable;
    }
}
