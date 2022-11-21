package com.epam.transport;

import java.util.Objects;

/**
 * Class Automobile that contains fields and methods and extends class Vehicle.
 */
public class Automobile extends Vehicle {

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Automobile() {
        super();
        setType(VehicleType.LAND);
    }

    public Automobile(int speed, int capacity, VehicleType vehicleType, String brand) {
        super(speed, capacity, vehicleType);
        setBrand(brand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automobile automobile = (Automobile) o;
        return Objects.equals(brand, automobile.brand);
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "brand='" + brand + '\'' +
                ", type=" + vehicleType +
                ", speed=" + getSpeed() +
                ", capacity=" + getCapacity() +
                '}';
    }
}
