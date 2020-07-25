package com.oocl.cultivation;

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
        LinkedList<Car> cars = parkingLot.getCars();
        Car customerCar=null;
        for(Car car:cars){
            if(car.getNumberPlate() == ticket.getId()){
                customerCar = car;
            }
        }
        return customerCar;

    }
}
