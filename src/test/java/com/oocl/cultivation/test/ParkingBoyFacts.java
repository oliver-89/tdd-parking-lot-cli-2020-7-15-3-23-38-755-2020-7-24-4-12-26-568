package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {


    //story1
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
        parkingBoy.getParkingLots().get(0).getCars().add(new Car("湘A562562"));
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



    @Test
    void should_return_null_when_parking_boy_fetching_given_1_car_1_parking_boy_1_full_parking_lot(){

        //given 1 car 1 parking boy 1 full parking lot
        Car car = new Car("湘A562562");
        ParkingBoy parkingBoy = new ParkingBoy();
        for(int i = 0;i<parkingBoy.getNumOfParkingLot();i++){
            parkingBoy.getParkingLots().get(i).setCapacity(10);
        }


        //when parking boy parking
        Ticket ticket = parkingBoy.parking(car);

        //then return null
        assertEquals(null,ticket);

    }

    //story2

    @Test
    void should_return_null_notify_unrecognized_parking_ticker_when_fetching_given_wrong_ticket_1_parking_boy(){

        //given wrong ticket 1 parking boy
        Ticket ticket = new Ticket("wrong");
        ParkingBoy parkingBoy = new ParkingBoy();

        //when parking boy fetching
        Car car = parkingBoy.fetching(ticket);
        String msg = parkingBoy.notify(ticket);

        //should return null notify unrecognized parking ticket
        assertEquals(null,car);
        assertEquals(msg,"unrecognized parking ticket");

    }

    @Test
    void should_return_null_notify_unrecognized_parking_ticker_when_fetching_given_used_ticket_1_parking_boy(){

        //given used ticket 1 parking boy
        Car car = new Car("湘A562562");
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = parkingBoy.parking(car);
        parkingBoy.fetching(ticket);

        //when parking boy fetching
        Car car2 = parkingBoy.fetching(ticket);
        String msg = parkingBoy.notify(ticket);

        //should return null notify unrecognized parking ticket
        assertEquals(null,car2);
        assertEquals(msg,"unrecognized parking ticket");

    }

    @Test
    void should_notify_provide_your_parking_ticket_when_fetching_given_no_ticket_1_parking_boy(){
        //given no ticket 1 parking boy
        ParkingBoy parkingBoy = new ParkingBoy();

        //when parking boy fetching
        Car car = parkingBoy.fetching(null);
        String msg = parkingBoy.notify(null);

        //then return null notify provide your parking ticket
        assertEquals(null,car);
        assertEquals("Please provide your parking ticket",msg);
    }

    @Test
    void should_notify_no_enough_position_when_parking_given_1_car_1_parking_boy_full_parking_lot(){
        //given 1 car 1 parking boy 1 full parking lot
        ParkingBoy parkingBoy = new ParkingBoy();
        for(int i = 0;i<parkingBoy.getNumOfParkingLot();i++){
            parkingBoy.getParkingLots().get(i).setCapacity(10);
        }

        Car car = new Car("湘A562562");

        //when parking boy fetching
        Ticket ticket = parkingBoy.parking(car);
        String msg = parkingBoy.notify(ticket);

        //then return null notify provide your parking ticket
        assertEquals(null,ticket);
        assertEquals("No enough position",msg);
    }


    @Test
    void should_return_ticket_when_parking_given_1_car_1_full_parking_lot_1_parking_boy(){
        //given 1 full parking lot 1 parking boy
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.getParkingLots().get(0).setCapacity(10);
        Car car = new Car("湘A782611");

        //when parking boy parking
        Ticket ticket = parkingBoy.parking(car);

        //then return ticket
        assertEquals("湘A782611",ticket.getId());

    }

    //story4

    @Test
    void should_parking_car_to_have_less_cars_parking_lot_when_smart_parking_boy_parking_given_1_more_cars_parking_lot_1_less_1_smart_parking_boy_1_car(){
        //given 1 more cars parking lot 1 less 1 smart parking boy 1 car
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.getParkingLots().get(0).setCapacity(2);
        smartParkingBoy.getParkingLots().get(1).setCapacity(5);
        Car car = new Car("湘A78178");

        //when smart parking boy parking
        smartParkingBoy.parking(car);
        boolean isExist = smartParkingBoy.getParkingLots().get(0).getCars().contains(car);

        //then car in have less cars parking lot
        assertEquals(true,isExist);

    }


    @Test
    void should_return_1_car_when_smart_parking_boy_fetching_given_1_ticket_1_smart_parking_boy() {
        //given 1 ticket 1 smart parking boy
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.getParkingLots().get(0).getCars().add(new Car("湘A562562"));
        Ticket ticket = new Ticket("湘A562562");

        //when smart parking boy fetching
        Car car = smartParkingBoy.fetching(ticket);
        //then return 1 car
        assertEquals("湘A562562",car.getNumberPlate());
    }

    @Test
    void should_return_2_tickets_when_smart_parking_boy_parking_given_2_cars_1_smart_parking_boy(){
        //given 2 cars 1 parking boy
        Car car = new Car("湘A562562");
        Car car2 = new Car("湘A562563");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

        //when parking boy parking
        Ticket ticket = smartParkingBoy.parking(car);
        Ticket ticket2 = smartParkingBoy.parking(car2);

        //then return 2 tickets
        assertEquals("湘A562562",ticket.getId());
        assertEquals("湘A562563",ticket2.getId());

    }

    @Test
    void should_return_null_when_smart_parking_boy_parking_given_wrong_ticket_1_smart_parking_boy(){
        //given wrong ticket 1 parking boy
        Ticket ticket = new Ticket("wrong");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

        //when parking boy parking
        Car car = smartParkingBoy.fetching(ticket);

        //then return null
        assertEquals(null,car);

    }

    @Test
    void should_return_null_when_smart_parking_boy_parking_given_used_ticket_1_smart_parking_boy(){
        //given used ticket 1 parking boy
        Car car = new Car("湘A562562");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Ticket ticket = smartParkingBoy.parking(car);
        smartParkingBoy.fetching(ticket);

        //when parking boy parking
        Car car2 = smartParkingBoy.fetching(ticket);

        //then return null
        assertEquals(null,car2);

    }


    @Test
    void should_return_null_when_smart_parking_boy_fetching_given_1_car_1_smart_parking_boy_full_parking_lot(){

        //given 1 car 1 parking boy 2 full parking lot
        Car car = new Car("湘A562562");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        for(int i = 0;i<smartParkingBoy.getNumOfParkingLot();i++){
            smartParkingBoy.getParkingLots().get(i).setCapacity(10);
        }


        //when parking boy parking
        Ticket ticket = smartParkingBoy.parking(car);

        //then return null
        assertEquals(null,ticket);

    }

    @Test
    void should_return_null_notify_unrecognized_parking_ticker_when_fetching_given_wrong_ticket_1_smart_parking_boy(){

        //given wrong ticket 1 parking boy
        Ticket ticket = new Ticket("wrong");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

        //when parking boy fetching
        Car car = smartParkingBoy.fetching(ticket);
        String msg = smartParkingBoy.notify(ticket);

        //should return null notify unrecognized parking ticket
        assertEquals(null,car);
        assertEquals(msg,"unrecognized parking ticket");

    }

    @Test
    void should_return_null_notify_unrecognized_parking_ticker_when_fetching_given_used_ticket_1_smart_parking_boy(){

        //given used ticket 1 parking boy
        Car car = new Car("湘A562562");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        Ticket ticket = smartParkingBoy.parking(car);
        smartParkingBoy.fetching(ticket);

        //when parking boy fetching
        Car car2 = smartParkingBoy.fetching(ticket);
        String msg = smartParkingBoy.notify(ticket);

        //should return null notify unrecognized parking ticket
        assertEquals(null,car2);
        assertEquals(msg,"unrecognized parking ticket");

    }

    @Test
    void should_notify_provide_your_parking_ticket_when_fetching_given_no_ticket_1_smart_parking_boy(){
        //given no ticket 1 parking boy
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

        //when parking boy fetching
        Car car = smartParkingBoy.fetching(null);
        String msg = smartParkingBoy.notify(null);

        //then return null notify provide your parking ticket
        assertEquals(null,car);
        assertEquals("Please provide your parking ticket",msg);
    }


    @Test

    void should_notify_no_enough_position_when_parking_given_1_car_1_smart_parking_boy_full_parking_lot(){
        //given 1 car 1 parking boy  full parking lot
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        for(int i = 0;i<smartParkingBoy.getNumOfParkingLot();i++){
            smartParkingBoy.getParkingLots().get(i).setCapacity(10);
        }

        Car car = new Car("湘A562562");

        //when parking boy fetching
        Ticket ticket = smartParkingBoy.parking(car);
        String msg = smartParkingBoy.notify(ticket);

        //then return null notify provide your parking ticket
        assertEquals(null,ticket);
        assertEquals("No enough position",msg);


    }

    //story 5

    @Test
    void should_park_in_large_available_position_rate_parking_lot_when_parking_given_super_smart_parking_boy_1_car(){
        //given 1 super smart parking boy 1 car 2 parking lot
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Car car = new Car("湘A28912");
        superSmartParkingBoy.getParkingLots().get(0).setCapacity(2);
        superSmartParkingBoy.getParkingLots().get(1).setCapacity(7);

        //when parking
        superSmartParkingBoy.parking(car);
        Boolean isExis = superSmartParkingBoy.getParkingLots().get(0).getCars().contains(car);
        //then park in large available 1
        assertEquals(true,isExis);
    }

    @Test
    void should_return_1_car_when_super_smart_parking_boy_fetching_given_1_ticket_1_super_smart_parking_boy() {
        //given 1 ticket 1 smart parking boy
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.getParkingLots().get(0).getCars().add(new Car("湘A562562"));
        Ticket ticket = new Ticket("湘A562562");

        //when smart parking boy fetching
        Car car = superSmartParkingBoy.fetching(ticket);
        //then return 1 car
        assertEquals("湘A562562",car.getNumberPlate());
    }

    @Test
    void should_return_2_tickets_when_super_smart_parking_boy_parking_given_2_cars_1_super_smart_parking_boy(){
        //given 2 cars 1 parking boy
        Car car = new Car("湘A562562");
        Car car2 = new Car("湘A562563");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();

        //when parking boy parking
        Ticket ticket = superSmartParkingBoy.parking(car);
        Ticket ticket2 = superSmartParkingBoy.parking(car2);

        //then return 2 tickets
        assertEquals("湘A562562",ticket.getId());
        assertEquals("湘A562563",ticket2.getId());

    }

    @Test
    void should_return_null_when_super_smart_parking_boy_parking_given_wrong_ticket_1_super_smart_parking_boy(){
        //given wrong ticket 1 parking boy
        Ticket ticket = new Ticket("wrong");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();

        //when parking boy parking
        Car car = superSmartParkingBoy.fetching(ticket);

        //then return null
        assertEquals(null,car);

    }

    @Test
    void should_return_null_when_super_smart_parking_boy_parking_given_used_ticket_1_super_smart_parking_boy(){
        //given used ticket 1 parking boy
        Car car = new Car("湘A562562");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Ticket ticket = superSmartParkingBoy.parking(car);
        superSmartParkingBoy.fetching(ticket);

        //when parking boy parking
        Car car2 = superSmartParkingBoy.fetching(ticket);

        //then return null
        assertEquals(null,car2);

    }

    @Test
    void should_return_null_when_super_smart_parking_boy_fetching_given_1_car_1_super_smart_parking_boy_full_parking_lot(){

        //given 1 car 1 parking boy 2 full parking lot
        Car car = new Car("湘A562562");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        for(int i = 0;i<superSmartParkingBoy.getNumOfParkingLot();i++){
            superSmartParkingBoy.getParkingLots().get(i).setCapacity(10);
        }


        //when parking boy parking
        Ticket ticket = superSmartParkingBoy.parking(car);

        //then return null
        assertEquals(null,ticket);

    }

    @Test
    void should_return_null_notify_unrecognized_parking_ticker_when_fetching_given_wrong_ticket_1_super_smart_parking_boy(){

        //given wrong ticket 1 parking boy
        Ticket ticket = new Ticket("wrong");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();

        //when parking boy fetching
        Car car = superSmartParkingBoy.fetching(ticket);
        String msg = superSmartParkingBoy.notify(ticket);

        //should return null notify unrecognized parking ticket
        assertEquals(null,car);
        assertEquals(msg,"unrecognized parking ticket");

    }

    @Test
    void should_return_null_notify_unrecognized_parking_ticker_when_fetching_given_used_ticket_1_super_smart_parking_boy(){

        //given used ticket 1 parking boy
        Car car = new Car("湘A562562");
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        Ticket ticket = superSmartParkingBoy.parking(car);
        superSmartParkingBoy.fetching(ticket);

        //when parking boy fetching
        Car car2 = superSmartParkingBoy.fetching(ticket);
        String msg = superSmartParkingBoy.notify(ticket);

        //should return null notify unrecognized parking ticket
        assertEquals(null,car2);
        assertEquals(msg,"unrecognized parking ticket");

    }

    @Test
    void should_notify_provide_your_parking_ticket_when_fetching_given_no_ticket_1_super_smart_parking_boy(){
        //given no ticket 1 parking boy
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();

        //when parking boy fetching
        Car car = superSmartParkingBoy.fetching(null);
        String msg = superSmartParkingBoy.notify(null);

        //then return null notify provide your parking ticket
        assertEquals(null,car);
        assertEquals("Please provide your parking ticket",msg);
    }


    @Test

    void should_notify_no_enough_position_when_parking_given_1_car_1_super_smart_parking_boy_full_parking_lot(){
        //given 1 car 1 parking boy  full parking lot
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        for(int i = 0;i<superSmartParkingBoy.getNumOfParkingLot();i++){
            superSmartParkingBoy.getParkingLots().get(i).setCapacity(10);
        }

        Car car = new Car("湘A562562");

        //when parking boy fetching
        Ticket ticket = superSmartParkingBoy.parking(car);
        String msg = superSmartParkingBoy.notify(ticket);

        //then return null notify provide your parking ticket
        assertEquals(null,ticket);
        assertEquals("No enough position",msg);


    }



}
