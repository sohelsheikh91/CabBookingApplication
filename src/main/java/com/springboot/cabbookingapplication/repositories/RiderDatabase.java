package com.springboot.cabbookingapplication.repositories;

import com.springboot.cabbookingapplication.exceptions.DriverNotFoundException;
import com.springboot.cabbookingapplication.model.Gender;
import com.springboot.cabbookingapplication.model.Location;
import com.springboot.cabbookingapplication.model.Rider;
import com.springboot.cabbookingapplication.model.User;

import java.util.HashMap;
import java.util.Map;

public class RiderDatabase {

    private static Map<Integer, User> riders = new HashMap<>();
    private static Integer userId = 0;

    public Rider add(String name, Gender gender, Integer age, Location location) {
        Rider newRider = new Rider(++userId, name, gender, age, location);
        riders.put(userId, newRider);
        return newRider;
    }

    public void update(Integer id, Rider rider) {

        if(!riders.containsKey(id))
            throw new DriverNotFoundException("Driver Does Not Exists");
        riders.put(id, rider);

    }

    public void updateLocation(Integer id, Location location) {
        if(!riders.containsKey(id))
            throw new DriverNotFoundException("Driver Does Not Exists");
        else {
            User rider = riders.get(id);
            rider.setLocation(location);
        }
    }
}
