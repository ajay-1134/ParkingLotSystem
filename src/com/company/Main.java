package com.company;

import java.util.Scanner;

public class Main {
    public static void welcomeCenter(ParkingLot parkingLot) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Hi! Welcome To Our BLACK-HEART Parking Lot =====");
        System.out.println("Please choose your purpose: press 1 for parking, press 2 for getting your vehicle.");
        boolean hasNextInt = scanner.hasNextInt();
        if (!hasNextInt) {
            System.out.println("Not a valid number. Please try again!");
            scanner.next();
        }
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("You've chosen to park your vehicle");
            enterParkingLot(parkingLot);
        } else if (choice == 2) {
            System.out.println("You've chosen to get your vehicle");
            leaveParkingLot(parkingLot);
        } else {
            welcomeCenter(parkingLot);
        }
    }

    public static void enterParkingLot(ParkingLot parkingLot) {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle;
        System.out.println("Please choose your car type. press 1 for CAR, press 2 for TRUCK.");
        boolean hasNextInt = scanner.hasNextInt();
        if (!hasNextInt) {
            System.out.println("Not a valid number. Please try again!");
            scanner.next();
        }
        int choice = scanner.nextInt();
        if (choice != 1 && choice != 2) {
            enterParkingLot(parkingLot);
        }
        System.out.println("Please enter you plate:");
        String plate = scanner.next();
        if (choice == 1) {
            vehicle = new Car(plate, "Car");
        } else {
            vehicle = new Truck(plate, "Truck");
        }
        if (parkingLot.load(vehicle)) {
            System.out.println("Success!");
        } else {
            System.out.println("Sorry, our parking lot is full now. Please try again later.");
        }
    }

    public static void leaveParkingLot(ParkingLot parkingLot) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your plate: ");
        String plate = scanner.next();
        Vehicle vehicle = parkingLot.getVehicle(plate);
        if (vehicle == null) {
            System.out.println("Vehicle not found. Press 1 to try again. Press 0 to exit.");
            boolean hasNextInt = scanner.hasNextInt();
            if (!hasNextInt) {
                System.out.println("Not a valid number. Please try again!");
                scanner.next();
            }
            int choice = scanner.nextInt();
            if (choice == 1) {
                leaveParkingLot(parkingLot);
            } else {
                System.exit(0);
            }
        } else {
            if (parkingLot.unload(vehicle)) {
                System.out.println("Thank you for visiting. Have a nice day!  :)");
            } else {
                System.out.println("Please Pay before you leave. Thank you!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = new ParkingLot();
        welcomeCenter(parkingLot);
        Thread.sleep(3000);
        welcomeCenter(parkingLot);
        Thread.sleep(3000);
        welcomeCenter(parkingLot);
        Thread.sleep(3000);
        welcomeCenter(parkingLot);
        Thread.sleep(3000);
        welcomeCenter(parkingLot);
    }
}

