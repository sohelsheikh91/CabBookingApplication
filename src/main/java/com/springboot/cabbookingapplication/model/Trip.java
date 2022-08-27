package com.springboot.cabbookingapplication.model;

import lombok.Data;

@Data
public class Trip {

    private Rider rider;
    private Driver driver;
    private Double price;
    private Location fromPoint;
    private Location toPoint;

    public Trip(Rider rider, Driver driver, Double price, Location fromPoint, Location toPoint) {
        this.rider = rider;
        this.driver = driver;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }

}
