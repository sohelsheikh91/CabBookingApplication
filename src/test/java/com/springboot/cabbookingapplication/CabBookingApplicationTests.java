package com.springboot.cabbookingapplication;

import com.springboot.cabbookingapplication.exceptions.NoRideFoundException;
import com.springboot.cabbookingapplication.model.*;
import com.springboot.cabbookingapplication.repositories.DriverRepository;
import com.springboot.cabbookingapplication.repositories.RiderRepository;
import com.springboot.cabbookingapplication.service.BillService;
import com.springboot.cabbookingapplication.service.CabService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CabBookingApplicationTests {
    CabService cabService;
    BillService billService;
    DriverRepository driverDatabase;
    RiderRepository riderDatabase;

    @BeforeEach
    void setUp() {

        billService = new BillService();
        driverDatabase = new DriverRepository();
        riderDatabase = new RiderRepository();
        cabService = new CabService(driverDatabase,riderDatabase,billService);
    }
    @Test
    void sample() {

        Rider abhishek = riderDatabase.add("Abhishek", Gender.M, 23, new Location(0.0,0.0));
        Rider rahul = riderDatabase.add("Rahul", Gender.M, 29, new Location(10.0,0.0));
        Rider nandini = riderDatabase.add("Nandini", Gender.F, 22, new Location(10.0,6.0));

        Vehicle vehicle1 = new Cab(VehicleType.CAR,"Swift, KA-01-12345");
        driverDatabase.add("Driver1",Gender.M, 22, new Location(10.0,1.0), vehicle1);
        Vehicle vehicle2 = new Cab(VehicleType.CAR,"Swift, KA-01-12348");
        driverDatabase.add("Driver2",Gender.F, 29, new Location(11.0,10.0), vehicle2);
        Vehicle vehicle3 = new Cab(VehicleType.CAR,"Swift, KA-01-12390");
        driverDatabase.add("Driver3",Gender.M, 24, new Location(5.0,3.0), vehicle3);


        Location abhishekLocation = abhishek.getLocation();

        assertThrows(NoRideFoundException.class, ()->cabService.findRide(abhishekLocation));

        Location rahulLocation = rahul.getLocation();
        List<Driver> driverList = cabService.findRide(rahulLocation);
        assertNotNull(driverList);

        Location rahulDestination = new Location(15.0,3.0);
        cabService.chooseRide(rahul, rahulDestination, driverList);

        System.out.println("Bill of Rahul is " + cabService.calculateBill(rahul));

        Location nandiniLocation = nandini.getLocation();
        driverList = cabService.findRide(nandiniLocation);

        Location nandiniDestination = new Location(25.0,3.0);
        cabService.chooseRide(nandini, nandiniDestination, driverList);

        System.out.println("Bill of nandini is " +cabService.calculateBill(nandini));

        System.out.println("Total Earning of All Drivers " + cabService.TotalEarningOfAllDrivers());

    }
}
