package com.epam.transport;

/**
 * Class Vehicle that contains fields and methods and extends class Transport.
 */
public class Vehicle extends Transport {
    VehicleType vehicleType;

    public VehicleType getType() {
        return vehicleType;
    }

    public void setType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle() {
        super();
    }

    public Vehicle(int speed, int capacity, VehicleType vehicleType) {
        super(speed, capacity);
        setType(vehicleType);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleType == vehicle.vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + vehicleType +
                ", speed=" + getSpeed() +
                ", capacity=" + getCapacity() +
                '}';
    }
}
