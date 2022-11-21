package com.epam.main;

import com.epam.container.TransportList;
import com.epam.transport.*;

import java.util.Iterator;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        TransportList<Automobile> transportList = new TransportList<>();
        transportList.add((new Automobile(200, 4, VehicleType.LAND, "Mazeratti")));
        transportList.add((new Automobile(150, 4, VehicleType.LAND, "Chudo")));


        Predicate<Automobile> transportPredicate = transport -> transport.getBrand().equals("Chudo");


        Iterator<Automobile> iterator =  transportList.iterator(transportPredicate);
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}