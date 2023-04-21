package com.epam.transport;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class Automobile that contains fields and methods and extends class Vehicle.
 */
public class Automobile extends Vehicle {
    private static final AtomicInteger increment = new AtomicInteger(0);
    private final int productId;
    private String brand;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Automobile() {
        this(0, 0, VehicleType.LAND, null);
    }

    public Automobile(int speed, int capacity, VehicleType vehicleType, String brand) {
        super(speed, capacity, vehicleType);
        setBrand(brand);
        productId = increment.incrementAndGet();
    }

    public Automobile(int speed, int capacity, VehicleType vehicleType, String brand, int price) {
        this(speed, capacity, vehicleType, brand);
        setPrice(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
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
                ", price=" + getPrice() +
                ", ID=" + getId() +
                '}';
    }
}
