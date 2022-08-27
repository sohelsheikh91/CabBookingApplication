package com.springboot.cabbookingapplication.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private String vehicleId;
    private VehicleType vehicleType;

    public Vehicle(VehicleType vehicleType, String vehicleId) {
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
    }
}
