package com.springboot.cabbookingapplication.service;

import com.springboot.cabbookingapplication.model.Driver;
import com.springboot.cabbookingapplication.model.Location;
import com.springboot.cabbookingapplication.model.Rider;

import java.util.List;

public interface VehicleService {

    List<Driver> findRide(Location location);
    void chooseRide(Rider rider, Location destiation, List<Driver> driverList);
    Double calculateBill(Rider rider);
    Double TotalEarningOfAllDrivers();
}
