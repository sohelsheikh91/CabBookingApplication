package com.springboot.cabbookingapplication.service;

import com.springboot.cabbookingapplication.exceptions.NoRideFoundException;
import com.springboot.cabbookingapplication.exceptions.NoTripFoundException;
import com.springboot.cabbookingapplication.model.*;
import com.springboot.cabbookingapplication.repositories.DriverDatabase;
import com.springboot.cabbookingapplication.repositories.RiderDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CabService implements VehicleService{

    public static final Double TRIP_MATCHING_DISTANCE = 5.0;
    private static Map<Integer, List<Trip>> trips = new HashMap<>();
    private static Double totalEarningByAllRiders = 0.0;

    private DriverDatabase driverDatabase;

    private RiderDatabase riderDatabase;

    private BillService billService;

    public CabService(DriverDatabase driverDatabase, RiderDatabase riderDatabase, BillService billService) {
        this.driverDatabase = driverDatabase;
        this.riderDatabase = riderDatabase;
        this.billService = billService;
    }



    public List<Driver> findRide(Location location) {

        List<Driver> driverList = driverDatabase.getCabs(location, TRIP_MATCHING_DISTANCE);
        if(driverList != null) {
            System.out.println("Cab is Available");
            return driverList;
        }
        else
            throw new NoRideFoundException("No Ride Found nearby");
    }

    public void chooseRide(Rider rider, Location destination, List<Driver> driverList) {

        List<Driver> availableCabs = driverList.stream()
                .filter(driver -> driver.getCar().getVehicleType() == VehicleType.CAR)
                .collect(Collectors.toList());

        if(!availableCabs.isEmpty()){
            Driver driver = availableCabs.get(0);
            Trip newTrip = new Trip(rider, driver, 0.0, rider.getLocation(), destination);

            Integer currentRiderId = rider.getId();
            if(!trips.containsKey(rider.getId()))
                trips.put(currentRiderId, new ArrayList<>());

            trips.get(currentRiderId).add(newTrip);
            rider.setLocation(destination);
            driver.setLocation(destination);

            riderDatabase.update(rider.getId(), rider);
            driverDatabase.update(driver.getId(), driver);
            driver.setIsAvailable(false);

            System.out.println("Ride Started with "+ driver.getName());
        }else
            throw new NoRideFoundException("No Ride Found nearby");

    }

    public Double calculateBill(Rider rider) {

        List<Trip> listOfTrips = trips.get(rider.getId());

        if(listOfTrips == null) throw new NoTripFoundException("No Trip found for this User");

        Integer lastIndex = listOfTrips.size()-1;

        Trip latestTrip = listOfTrips.get(lastIndex);
        Double currentPrice = billService.findPrice(latestTrip.getFromPoint(), latestTrip.getToPoint());

        latestTrip.setPrice(currentPrice);

        return currentPrice;
    }
    public Double TotalEarningOfAllDrivers() {
        Double totalAmount = 0.0;
        for(Integer riderId : trips.keySet()){

            totalAmount += trips.get(riderId).stream().mapToDouble(trip -> trip.getPrice()).sum();
        }
        return totalAmount;
    }
}
