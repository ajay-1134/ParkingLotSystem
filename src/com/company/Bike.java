package com.company;

public class Bike extends Vehicle {
    public Bike(String plate, String type) { //type = sedan, cooper etc.
        super(plate, type);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
