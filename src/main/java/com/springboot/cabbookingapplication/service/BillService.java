package com.springboot.cabbookingapplication.service;

import com.springboot.cabbookingapplication.model.Location;
import org.springframework.stereotype.Component;

public class BillService {

    public static final Double PER_KM_RATE = 10.0;
    public Double findPrice(Location fromPoint, Location toPoint) {
        return fromPoint.distance(toPoint) * PER_KM_RATE;
    }
}
