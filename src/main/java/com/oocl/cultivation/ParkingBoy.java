package com.oocl.cultivation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy() {
        this.parkingLot = new ParkingLot();
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public Ticket parking(Car car){
        Ticket ticket = new Ticket(car.getNumberPlate());
        parkingLot.getCars().add(car);
        return  ticket;
    }

    public Car fetching(Ticket ticket){
        ArrayList<Car> cars = parkingLot.getCars();
        Car customerCar=null;
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()){
            Car car = iterator.next();
            if(car.getNumberPlate() == ticket.getId()){
                customerCar = car;
                iterator.remove();
            }
        }
        return customerCar;

    }
}
