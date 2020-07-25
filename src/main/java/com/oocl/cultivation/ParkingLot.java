package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParkingLot {
    private int totalNumber;
    private ArrayList<Car> cars;

    public ParkingLot() {
        this.totalNumber = 0;
        this.cars = new ArrayList<Car>();
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
