package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {


    //given used ticket 1 parking boy
    //when fetch car
    //then return null


    //given 1 car 1 parking boy 1 full parking lot
    //when parking boy parking
    //then return null
    @Test
    void should_return_1_ticket_when_parking_boy_parking_given_1_car_1_parking_boy() {
        //given 1 car 1 parking boy
        Car car = new Car("湘A562562");
        ParkingBoy parkingBoy = new ParkingBoy();
        //when parking boy parking
        Ticket result = parkingBoy.parking(car);
        //then return 1 ticket
        assertEquals(car.getNumberPlate(),result.getId());
    }

    @Test
    void should_return_1_car_when_parking_boy_fetching_given_1_ticket_1_parking_boy() {
        //given 1 ticket 1 parking boy
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.getParkingLot().getCars().add(new Car("湘A562562"));

        Ticket ticket = new Ticket("湘A562562");

        //when parking boy fetching
        Car result = parkingBoy.fetching(ticket);
        //then return 1 car
        assertEquals("湘A562562",result.getNumberPlate());
    }

    @Test
    void should_return_2_tickets_when_parking_boy_parking_given_2_cars_1_parking_boy(){
        //given 2 cars 1 parking boy
        Car car = new Car("湘A562562");
        Car car2 = new Car("湘A562563");
        ParkingBoy parkingBoy = new ParkingBoy();

        //when parking boy parking
        Ticket ticket = parkingBoy.parking(car);
        Ticket ticket2 = parkingBoy.parking(car2);

        //then return 2 tickets
        assertEquals("湘A562562",ticket.getId());
        assertEquals("湘A562563",ticket2.getId());

    }

    @Test
    void should_return_null_when_parking_boy_parking_given_wrong_ticket_1_parking_boy(){
        //given wrong ticket 1 parking boy
        Ticket ticket = new Ticket("wrong");
        ParkingBoy parkingBoy = new ParkingBoy();

        //when parking boy parking
        Car car = parkingBoy.fetching(ticket);

        //then return null
        assertEquals(null,car);

    }


    @Test
    void should_return_null_when_parking_boy_parking_given_used_ticket_1_parking_boy(){
        //given used ticket 1 parking boy
        Car car = new Car("湘A562562");
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = parkingBoy.parking(car);
        parkingBoy.fetching(ticket);

        //when parking boy parking
        Car car2 = parkingBoy.fetching(ticket);

        //then return null
        assertEquals(null,car2);

    }

}
