package lab4;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus(3);
        Taxi taxi = new Taxi(2);
        FireTruck fireTruck = new FireTruck(2);
        PoliceCar policeCar = new PoliceCar(2);

        bus.board(new CommonPassenger("Олена"));
        bus.board(new Firefighter("Іван"));
        taxi.board(new Policeman("Петро"));
        fireTruck.board(new Firefighter("Андрій"));
        policeCar.board(new Policeman("Марія"));

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        System.out.println("Людей на дорозі: " + road.getCountOfHumans());

        try {
            Taxi full = new Taxi(1);
            full.board(new CommonPassenger("A"));
            full.board(new CommonPassenger("B"));
        } catch (NoFreeSeatsException e) {
            System.out.println("Виняток: " + e.getMessage());
        }

        try {
            bus.disembark(new CommonPassenger("Невідомий"));
        } catch (PassengerNotFoundException e) {
            System.out.println("Виняток: " + e.getMessage());
        }
    }
}
