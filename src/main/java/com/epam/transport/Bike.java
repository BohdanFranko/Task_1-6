package com.epam.transport;

import java.util.Objects;

/**
 * Class Bike that contains fields and methods and extends class Vehicle.
 */
public class Bike extends Vehicle {

    private String typeOfBike;

    public String getTypeOfBike() {
        return typeOfBike;
    }

    public void setTypeOfBike(String typeOfBike) {
        this.typeOfBike = typeOfBike;
    }

    public Bike() {
        setType(VehicleType.LAND);
    }

    public Bike(String typeOfBike) {
        this();
        setTypeOfBike(typeOfBike);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bike bike = (Bike) o;
        return Objects.equals(typeOfBike, bike.typeOfBike);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "typeOfBike='" + typeOfBike + '\'' +
                ", type=" + vehicleType +
                ", speed=" + getSpeed() +
                ", capacity=" + getCapacity() +
                '}';
    }
}
