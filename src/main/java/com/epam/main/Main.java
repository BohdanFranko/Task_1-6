package com.epam.main;

import com.epam.string.MyStringHashIsLength;
import com.epam.string.MyStringHashIsSumOfChar;
import com.epam.transport.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyStringHashIsLength firstLength = new MyStringHashIsLength("First");
        MyStringHashIsSumOfChar firstSum = new MyStringHashIsSumOfChar("First");
        MyStringHashIsLength secondLength = new MyStringHashIsLength("Second");
        MyStringHashIsSumOfChar secondSum = new MyStringHashIsSumOfChar("Second");
        MyStringHashIsLength thirdLength = new MyStringHashIsLength("Third");
        MyStringHashIsSumOfChar thirdSum = new MyStringHashIsSumOfChar("Third");

        Automobile maserati = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        Automobile sedan = new Automobile(1500, 100, VehicleType.LAND, "Sedan");
        Automobile lada = new Automobile(1500, 120, VehicleType.LAND, "Lada");

        HashMap<MyStringHashIsLength, Automobile> hashMapByLength = new HashMap<>();
        HashMap<MyStringHashIsSumOfChar, Automobile> hashMapBySum = new HashMap<>();
        LinkedHashMap<MyStringHashIsLength, Automobile> linkedHashMapByLength = new LinkedHashMap<>();
        LinkedHashMap<MyStringHashIsSumOfChar, Automobile> linkedHashMapBySum = new LinkedHashMap<>();

        hashMapByLength.put(firstLength, maserati);
        hashMapBySum.put(firstSum, maserati);
        linkedHashMapByLength.put(firstLength, maserati);
        linkedHashMapBySum.put(firstSum, maserati);

        hashMapByLength.put(secondLength, lada);
        hashMapBySum.put(secondSum, lada);
        linkedHashMapByLength.put(secondLength, lada);
        linkedHashMapBySum.put(secondSum, lada);

        hashMapByLength.put(thirdLength, sedan);
        hashMapBySum.put(thirdSum, sedan);
        linkedHashMapByLength.put(thirdLength, sedan);
        linkedHashMapBySum.put(thirdSum, sedan);

        System.out.println("-------------------HashMap-------------------------");
        printMap(hashMapByLength, hashMapBySum);
        System.out.println("-------------------LinkedHashMap-------------------------");
        printMap(linkedHashMapByLength, linkedHashMapBySum);
        System.out.println("------------------TestStrings-----------------");
        MyStringHashIsSumOfChar checkSum = new MyStringHashIsSumOfChar("!fdsa");
        System.out.println("CharSum1 = " + ('!' + 'f' + 'd' + 's'));
        MyStringHashIsSumOfChar checkSum1 = new MyStringHashIsSumOfChar("f[j34");
        System.out.println("CharSum2 = " + ('f' + '[' + 'j' + '3'));
        System.out.println(checkSum.hashCode() == checkSum1.hashCode());
        System.out.println(checkSum.equals(checkSum1));
        MyStringHashIsLength checkLength = new MyStringHashIsLength("58437");
        MyStringHashIsLength checkLength1= new MyStringHashIsLength("58f37");
        System.out.println(checkLength.hashCode() == checkLength1.hashCode());
        System.out.println(checkLength.equals(checkLength1));
    }

    private static void printMap(HashMap<MyStringHashIsLength, Automobile> hashMapByLength, HashMap<MyStringHashIsSumOfChar, Automobile> hashMapBySum) {
        for (Map.Entry<MyStringHashIsLength, Automobile> myStringAutomobileEntry : hashMapByLength.entrySet()) {
            System.out.println(myStringAutomobileEntry.getKey() + " " + myStringAutomobileEntry.getValue());
        }
        System.out.println("--------------------------------------------------");
        for (Map.Entry<MyStringHashIsSumOfChar, Automobile> myStringHashIsSumOfCharAutomobileEntry : hashMapBySum.entrySet()) {
            System.out.println(myStringHashIsSumOfCharAutomobileEntry.getKey() + " " + myStringHashIsSumOfCharAutomobileEntry.getValue());
        }
    }
}