package com.oocl.cultivation;

import java.util.LinkedList;
import java.util.Map;

public class ParkingLot {
    private int totalNumber;
    private LinkedList<Car> cars;

    public ParkingLot() {
        this.totalNumber = 0;
        this.cars = new LinkedList<Car>();
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public LinkedList<Car> getCars() {
        return cars;
    }
}
