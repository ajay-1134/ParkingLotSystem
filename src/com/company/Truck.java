package com.company;

public class Truck extends Vehicle {

    public Truck(String plate, String type) {   //type = SUV etc.
        super(plate, type);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}