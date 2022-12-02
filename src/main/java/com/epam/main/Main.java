package com.epam.main;

import com.epam.container.TransportList;
import com.epam.string.MyString;
import com.epam.transport.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        MyString first = new MyString(new String("First"));
        MyString second = new MyString(new String("Second"));
        MyString third = new MyString(new String("Third"));
        
        Automobile maserati = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        Automobile sedan = new Automobile(1500, 100, VehicleType.LAND, "Sedan");
        Automobile lada = new Automobile(1500, 120, VehicleType.LAND, "Lada");

        HashMap<MyString, Automobile> hashMap = new HashMap<>();
        LinkedHashMap<MyString, Automobile> linkedHashMap = new LinkedHashMap<>();
        
        hashMap.put(first, maserati);
        linkedHashMap.put(first, maserati);

        hashMap.put(second, lada);
        linkedHashMap.put(second, lada);

        hashMap.put(third, sedan);
        linkedHashMap.put(third, sedan);
        System.out.println("-------------------HashMap-------------------------");
        for (Map.Entry<MyString, Automobile> myStringAutomobileEntry : hashMap.entrySet()) {
            System.out.println(myStringAutomobileEntry.getKey() + " " + myStringAutomobileEntry.getValue());
        }
        System.out.println("-------------------LinkedHashMap-------------------------");
        for (Map.Entry<MyString, Automobile> myStringAutomobileEntry : linkedHashMap.entrySet()) {
            System.out.println(myStringAutomobileEntry.getKey() + " " + myStringAutomobileEntry.getValue());
        }
    }
}