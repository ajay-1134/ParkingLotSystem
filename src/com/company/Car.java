package com.company;

public class Car extends Vehicle {

    public Car(String plate, String type) { //type = sedan, cooper etc.
        super(plate, type);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}

