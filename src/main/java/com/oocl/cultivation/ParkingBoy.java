package com.oocl.cultivation;

public class ParkingBoy {

    public Ticket parking(Car car){
        Ticket ticket = new Ticket(car.getNumberPlate());
        return  ticket;
    }

    public Car fetching(Ticket ticket){


    }
}
