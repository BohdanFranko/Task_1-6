package com.epam.command.serialize;

import com.epam.container.TransportList;
import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;

import java.util.Scanner;

public class AddGoodAutoCommand implements SerializeCommand {
    @Override
    public void execute(Scanner scanner, TransportList<Automobile> transportList) {
        transportList.add(makeRandomAuto());
    }

    private Automobile makeRandomAuto() {
        Double minValue = 0D;
        Double maxValue = Long.MAX_VALUE * 1.0;
        String name = "Auto " + (Math.random() * (maxValue - minValue + 1) + minValue);
        int speed = (int) (Math.random() * (maxValue - minValue + 1) + minValue);
        int capacity = (int) (Math.random() * (maxValue - minValue + 1) + minValue);
        int price = (int) (Math.random() * (maxValue - minValue + 1) + minValue);
        return new Automobile(speed, capacity, VehicleType.LAND, name, price);
    }
}
