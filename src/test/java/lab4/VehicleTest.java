package lab4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.List;
import org.junit.Test;

public class VehicleTest {

    @Test
    public void busAcceptsAllPassengerTypes() {
        Bus bus = new Bus(5);
        bus.board(new CommonPassenger("Олена"));
        bus.board(new Firefighter("Іван"));
        bus.board(new Policeman("Петро"));
        assertEquals(5, bus.getMaxSeats());
        assertEquals(3, bus.getOccupiedSeats());
    }

    @Test
    public void taxiAcceptsAllPassengerTypes() {
        Taxi taxi = new Taxi(3);
        taxi.board(new CommonPassenger("Олена"));
        taxi.board(new Firefighter("Іван"));
        taxi.board(new Policeman("Петро"));
        assertEquals(3, taxi.getOccupiedSeats());
    }

    @Test
    public void fireTruckAcceptsFirefighters() {
        FireTruck truck = new FireTruck(2);
        truck.board(new Firefighter("Андрій"));
        truck.board(new Firefighter("Богдан"));
        assertEquals(2, truck.getOccupiedSeats());
    }

    @Test
    public void policeCarAcceptsPolicemen() {
        PoliceCar car = new PoliceCar(2);
        car.board(new Policeman("Марія"));
        car.board(new Policeman("Сергій"));
        assertEquals(2, car.getOccupiedSeats());
    }

    @Test
    public void boardingWhenFullThrowsException() {
        Taxi taxi = new Taxi(1);
        taxi.board(new CommonPassenger("A"));
        assertThrows(NoFreeSeatsException.class, () -> taxi.board(new CommonPassenger("B")));
        assertEquals(1, taxi.getOccupiedSeats());
    }

    @Test
    public void disembarkingRemovesPassenger() {
        Bus bus = new Bus(3);
        Passenger p = new CommonPassenger("Олена");
        bus.board(p);
        bus.disembark(p);
        assertEquals(0, bus.getOccupiedSeats());
    }

    @Test
    public void disembarkingAbsentPassengerThrowsException() {
        FireTruck truck = new FireTruck(2);
        truck.board(new Firefighter("Андрій"));
        assertThrows(PassengerNotFoundException.class,
                () -> truck.disembark(new Firefighter("Чужий")));
        assertEquals(1, truck.getOccupiedSeats());
    }

    @Test
    public void seatFreedAfterDisembarkCanBeReused() {
        PoliceCar car = new PoliceCar(1);
        Policeman first = new Policeman("Марія");
        car.board(first);
        car.disembark(first);
        car.board(new Policeman("Сергій"));
        assertEquals(1, car.getOccupiedSeats());
    }

    @Test
    public void boardAllAcceptsSubtypesOfT() {
        Bus bus = new Bus(4);
        List<Firefighter> firefighters = List.of(new Firefighter("Іван"), new Firefighter("Андрій"));
        bus.boardAll(firefighters);
        assertEquals(2, bus.getOccupiedSeats());
    }

    @Test
    public void nullPassengerRejected() {
        Bus bus = new Bus(2);
        assertThrows(IllegalArgumentException.class, () -> bus.board(null));
    }

    @Test
    public void invalidSeatCountRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Bus(0));
    }

    @Test
    public void roadCountsHumansInAllVehicles() {
        Road road = new Road();
        assertEquals(0, road.getCountOfHumans());

        Bus bus = new Bus(5);
        bus.board(new CommonPassenger("Олена"));
        bus.board(new Policeman("Петро"));
        Taxi taxi = new Taxi(3);
        taxi.board(new Firefighter("Іван"));
        FireTruck truck = new FireTruck(4);
        truck.board(new Firefighter("Андрій"));
        truck.board(new Firefighter("Богдан"));
        PoliceCar car = new PoliceCar(2);
        car.board(new Policeman("Марія"));

        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(truck);
        road.addCarToRoad(car);

        assertEquals(6, road.getCountOfHumans());

        bus.disembark(bus.getPassengers().get(0));
        assertEquals(5, road.getCountOfHumans());
    }
}
