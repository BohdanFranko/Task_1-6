package com.epam.transport;

/**
 * Class Transport that contains fields and methods to describe a Transport.
 */
public class Transport {
    private final int DEFAULT_MIN = 0;

    private int speed;
    private int capacity;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Transport() {
        setSpeed(DEFAULT_MIN);
        setCapacity(DEFAULT_MIN);
    }

    public Transport(int speed, int capacity) {
        setSpeed(speed);
        setCapacity(capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return speed == transport.speed && capacity == transport.capacity;
    }

    @Override
    public String toString() {
        return "main.transport.Transport{" +
                "speed=" + speed +
                ", capacity=" + capacity +
                '}';
    }
}
