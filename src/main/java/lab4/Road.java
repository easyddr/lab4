package lab4;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Passenger>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Passenger> car : carsInRoad) {
            count += car.getOccupiedSeats();
        }
        return count;
    }

    public void addCarToRoad(Vehicle<? extends Passenger> car) {
        if (car == null) {
            throw new IllegalArgumentException("Транспортний засіб не може бути null");
        }
        carsInRoad.add(car);
    }
}
