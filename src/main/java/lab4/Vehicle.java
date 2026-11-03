package lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Vehicle<T extends Passenger> {
    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    protected Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути додатною");
        }
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void board(T passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Пасажир не може бути null");
        }
        if (passengers.size() >= maxSeats) {
            throw new NoFreeSeatsException(
                    "Немає вільних місць у " + getClass().getSimpleName()
                            + " (місць: " + maxSeats + ")");
        }
        passengers.add(passenger);
    }

    public void boardAll(Collection<? extends T> group) {
        for (T p : group) {
            board(p);
        }
    }

    public void disembark(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException(
                    "Пасажира " + passenger + " немає в " + getClass().getSimpleName());
        }
    }

    public List<T> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }
}
