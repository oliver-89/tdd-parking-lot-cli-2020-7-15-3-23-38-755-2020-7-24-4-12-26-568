package com.oocl.cultivation;

import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy{



    public Ticket parking(Car car){
        ParkingLot parkingLot = findLessParkingLot(getParkingLots());
        Ticket ticket = new Ticket(car.getNumberPlate());
        parkingLot.getCars().add(car);
        parkingLot.setCapacity(parkingLot.getCapacity()+1);
        return  ticket;


    }

    public ParkingLot findLessParkingLot(ArrayList<ParkingLot> parkingLots){
        ParkingLot parkingLot = null;
        int capacity = 10;
        for(ParkingLot thisParkingLot:parkingLots){
            if(capacity>thisParkingLot.getCapacity()){
                parkingLot = thisParkingLot;
                capacity = thisParkingLot.getCapacity();
            }
        }
        return  parkingLot;
    }
}
