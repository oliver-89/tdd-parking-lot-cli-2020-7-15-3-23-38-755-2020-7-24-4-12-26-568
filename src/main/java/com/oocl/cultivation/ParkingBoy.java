package com.oocl.cultivation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ParkingBoy {

    private ArrayList<ParkingLot> parkingLots;
    private int numOfParkingLot;

    public ParkingBoy() {
        this.numOfParkingLot =2;
        this.parkingLots = new ArrayList<ParkingLot>();
        for(int i = 1;i<numOfParkingLot+1;i++){
            parkingLots.add(new ParkingLot());
        }
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public int getNumOfParkingLot() {
        return numOfParkingLot;
    }


    public Ticket parking(Car car){
        Ticket ticket =null;
        for(ParkingLot parkingLot:parkingLots){
            if (ticket==null&&parkingLot.getCapacity()<10){
                ticket = new Ticket(car.getNumberPlate());
                parkingLot.getCars().add(car);
                parkingLot.setCapacity(parkingLot.getCapacity()+1);
                break;
            }
        }

        return  ticket;
    }

    public Car fetching(Ticket ticket){
        Car customerCar=null;
        for(ParkingLot parkingLot:parkingLots){
            ArrayList<Car> cars = parkingLot.getCars();

            Iterator<Car> iterator = cars.iterator();
            while (iterator.hasNext()){
                Car car = iterator.next();
                if(car.getNumberPlate() == ticket.getId()){
                    customerCar = car;
                    iterator.remove();
                    parkingLot.setCapacity(parkingLot.getCapacity()-1);
                }
            }
        }
        return customerCar;
    }

    public String notify(Ticket ticket){
        String msg = null;
        int totalCapacity = 0;
        for(ParkingLot parkingLot:parkingLots){
            totalCapacity += parkingLot.getCapacity();
        }
        if(ticket ==null && totalCapacity==10*numOfParkingLot){
            msg="No enough position";
        }else if(ticket==null){
            msg="Please provide your parking ticket";

        }else if(fetching(ticket)==null){
            msg = "unrecognized parking ticket";
        }
        return msg;
    }
}
