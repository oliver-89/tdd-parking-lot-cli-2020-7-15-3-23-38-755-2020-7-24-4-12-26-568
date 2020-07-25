package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParkingLot {
    private int capacity;
    private ArrayList<Car> cars;

    public ParkingLot() {
        this.capacity = 0;
        this.cars = new ArrayList<Car>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
