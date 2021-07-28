package com.company;

import java.util.*;

public class ParkingLot {
    private static final int CAR_LOTS_CAPACITY = 3;
    private static final int TRUCK_LOTS_CAPACITY = 1;

    private List<Car> carLots = new ArrayList<>(CAR_LOTS_CAPACITY);
    private List<Truck> truckLots = new ArrayList<>(TRUCK_LOTS_CAPACITY);
    private int currentTruckAvailability;
    private int currentCarAvailability;

    public ParkingLot() {
        this.currentTruckAvailability = TRUCK_LOTS_CAPACITY;
        this.currentCarAvailability = CAR_LOTS_CAPACITY;
    }

    public Vehicle getVehicle(String plate) {
        for (Car car : carLots) {
            if (car.getPlate().equals(plate)) {
                return car;
            }
        }
        for (Truck truck  : truckLots) {
            if (truck.getPlate().equals(plate)) {
                return truck;
            }
        }
        return null;
    }

    public boolean isAvailable(Vehicle vehicle) {
        if (vehicle.getType().equals("Car")) {
            if (currentCarAvailability > 0) {
                System.out.println("Car Lots available amount: " + currentCarAvailability);
                currentCarAvailability --;
                carLots.add((Car)vehicle);
                return true;
            }
        }
        else if (vehicle.getType().equals("Truck")) {
            if (currentTruckAvailability > 0) {
                System.out.println("Car Lots available amount: " + currentTruckAvailability);
                currentTruckAvailability --;
                truckLots.add((Truck)vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean load(Vehicle vehicle) {
        if (!isAvailable(vehicle)) {
            return false;
        }
        vehicle.getTicket().setEnterTime(System.currentTimeMillis());
        System.out.println("This is a " + vehicle.getType() + " of which plate is " + vehicle.getPlate()
                + ", entering at " + new Date(vehicle.getTicket().getEnterTime()));
        return true;
    }

    public boolean isPaid(Vehicle vehicle) {
        int rate = vehicle.getTicket().getRate();
        System.out.println("Your rate is " + rate + " dollars / hour. Less than 1 hour counts as 1 hour.");
        vehicle.getTicket().setExitTime(System.currentTimeMillis());
        System.out.println("You are leaving at " + new Date(vehicle.getTicket().getExitTime()));
        long parkingTime = vehicle.getTicket().getExitTime() - vehicle.getTicket().getEnterTime();
//        int secs = new Date(parkingTime).getSeconds();
//        int mins = new Date(parkingTime).getMinutes();
//        int hours = new Date(parkingTime).getHours();
        int secs = (int) (parkingTime / 1000) % 60 ;
        int mins = (int) ((parkingTime / (1000 * 60)) % 60);
        int hours   = (int) ((parkingTime / (1000 * 60 * 60)) % 24);
        System.out.println("The parking time is "+ hours + "h " + mins + "mins "
                + secs + "secs.");
        if (secs != 0 || mins != 0) {
            hours ++;
        }
        int amount = hours * rate;
        System.out.println("You need to pay "+ amount + " dollars.");
        return true;
    }

    public boolean unload(Vehicle vehicle) {
        if (!isPaid(vehicle)) {
            return false;
        }
        if (vehicle.getType().equals("Car")) {
            currentCarAvailability ++;
            System.out.println("Car Lots available amount: " + currentCarAvailability);
            carLots.remove((Car)vehicle);
        } else if (vehicle.getType().equals("Truck")) {
            currentTruckAvailability ++;
            System.out.println("Truck Lots available amount: " + currentTruckAvailability);
            truckLots.remove((Truck)vehicle);
        }
        return true;
    }
}
