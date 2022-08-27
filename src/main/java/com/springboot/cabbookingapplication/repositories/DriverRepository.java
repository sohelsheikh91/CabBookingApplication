package com.springboot.cabbookingapplication.repositories;

import com.springboot.cabbookingapplication.exceptions.DriverNotFoundException;
import com.springboot.cabbookingapplication.exceptions.NoRideFoundException;
import com.springboot.cabbookingapplication.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DriverRepository {

    private static Map<Integer, Driver> drivers = new HashMap<>();
    private static Integer userId = 0;

    public void add(String name, Gender gender, Integer age, Location location, Vehicle vehicle) {
        Driver newDriver = new Driver(++userId, name, gender, age, location, vehicle,true);
        drivers.put(userId, newDriver);
    }

    public void update(Integer driverId, Driver driver) {

        if(!drivers.containsKey(driverId))
            throw new DriverNotFoundException("Driver Does Not Exists");
        drivers.put(driverId, driver);
    }

    public void updateLocation(Integer driverId, Location location) {

        if(!drivers.containsKey(driverId))
            throw new DriverNotFoundException("Driver Does Not Exists");
        else {
            User driver = drivers.get(driverId);
            driver.setLocation(location);
        }
    }

    public void updateDriverAvailability(String driverId, Boolean newAvailability) {
        if (!drivers.containsKey(driverId)) {
            throw new DriverNotFoundException("Driver Does Not Exists");
        }
        drivers.get(driverId).setIsAvailable(newAvailability);
    }

    public List<Driver> getCabs(Location fromPoint, Double distance) {

        List<Driver> result = new ArrayList<>();
        for (Driver cab : drivers.values()) {

            if (cab.getIsAvailable() && cab.getLocation().distance(fromPoint) <= distance) {
                result.add(cab);
            }
        }
        if(result.isEmpty()) throw new NoRideFoundException("No Rides Found");
        return result;
    }

    public List<Driver> getAvailabeCabs(Location location) {
        List<Driver> result = new ArrayList<>();
        for (Driver cab : drivers.values()) {

            if (cab.getIsAvailable()) {
                result.add(cab);
            }
        }
        return result;
    }
}
