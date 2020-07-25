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
        Ticket ticket =null;
        if(parkingLot.getCapacity()<10){
            ticket = new Ticket(car.getNumberPlate());
            parkingLot.getCars().add(car);
            parkingLot.setCapacity(parkingLot.getCapacity()+1);
        }
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
                parkingLot.setCapacity(parkingLot.getCapacity()-1);
            }
        }
        return customerCar;
    }

    public String notify(Ticket ticket){
        String msg = null;
        if(fetching(ticket)==null){
            msg = "unrecognized parking ticket";
        }
        return msg;
    }
}
