package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {
    //AC1：停车男孩可以将汽车停放在停车场中，并返还停车票。 客户可以将停车票还给停车男孩以取回汽车。
    //given 1 car 1 parking boy
    //when parking boy parking
    //then return 1 ticket

    //given 1 ticket 1 parking boy
    //when fetch car
    //then return 1 car

    //AC2：停车男孩可以将多辆车停放在停车场中。 并可以使用相应的票来取回正确的汽车。
    //given 2 car 1 pariking boy
    //when parking boy parking
    //then return 2 tickets

    //given 2 tickets 1 parking boy
    //when fetch car
    //then return 2 cars

    //AC3：如果客户给错了票（停车男孩不提供票）或不给票。 然后，任何车都不应取。
    //give wrong ticket 1 parking boy
    //when fetch car
    //then return null

    //AC4：如果客户提供已使用的票证。 然后，任何车都不应取。
    //given used ticket 1 parking boy
    //when fetch car
    //then return null

    //AC5：该停车场具有一定的容量（停车场的默认容量为10）。 如果没有位置，则用户无法将汽车停放在其中。 因此，他将不会获得任何入场券。
    //given 1 car 1 parking boy 1 full parking lot
    //when parking boy parking
    //then return null
    @Test
    void should_return_a_ticket_when_customer_parking_given_a_car() {
        //given 1 car 1 parking boy
        Car car = new Car(0);
        ParkingBoy parkingBoy = new ParkingBoy();
        //when parking boy parking
        Ticket result = parkingBoy.parking(car);
        //then return 1 ticket
        assertEquals(car.getNumberPlate(),result.getNumberPlate());


    }
}
