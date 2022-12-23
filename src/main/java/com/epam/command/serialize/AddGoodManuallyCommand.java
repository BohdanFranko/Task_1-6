package com.epam.command.serialize;

import com.epam.container.TransportList;
import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;

import java.util.Scanner;

public class AddGoodManuallyCommand implements SerializeCommand {
    @Override
    public void execute(Scanner scanner, TransportList<Automobile> transportList) {
        String name = scanner.nextLine();
        transportList.add(new Automobile(0, 0, VehicleType.LAND, name, 0));
    }
}
