package com.oocl.cultivation;

import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy{
    public Ticket parking(Car car){
        if(getTotalNumOfCars()<getNumOfParkingLot()*10){
            ParkingLot parkingLot = findHighRateParkingLot(getParkingLots());
            Ticket ticket = new Ticket(car.getNumberPlate());
            parkingLot.getCars().add(car);
            parkingLot.setCapacity(parkingLot.getCapacity()+1);
            return  ticket;
        }
        return null;


    }
    public int getTotalNumOfCars(){
        int num =0 ;
        for(ParkingLot parkingLot:getParkingLots()){
            num += parkingLot.getCapacity();
        }
        return num;
    }


    public ParkingLot findHighRateParkingLot(ArrayList<ParkingLot> parkingLots){
        ParkingLot parkingLot = null;
        double rate = 0.0;
        for(ParkingLot thisParkingLot:parkingLots){
            double thisRate =(10-thisParkingLot.getCapacity())*1.0/10 ;
            if(thisRate>=rate){
                parkingLot = thisParkingLot;
                rate = thisRate;
            }
        }
        return  parkingLot;
    }

}
