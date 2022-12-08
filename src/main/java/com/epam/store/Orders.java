package com.epam.store;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

public class Orders {

    private TreeMap<LocalDateTime, HashMap<Integer, Integer>> container;

    public Orders() {
        container = new TreeMap<>();
    }

    public boolean add(LocalDateTime date, HashMap<Integer, Integer> list) {
        container.put(date, list);
        return true;
    }

    public void print() {
        System.out.println("------------Orders------------");
        container.forEach(this::printOrderToString);
    }

    public void printInDateRange(LocalDateTime date1, LocalDateTime date2) {
        System.out.println("------------From Date1 to Date2------------");
        for (Map.Entry<LocalDateTime, HashMap<Integer, Integer>> localDateTimeHashMapEntry : container.entrySet()) {
            LocalDateTime tempDate = localDateTimeHashMapEntry.getKey();
            if ((tempDate.isAfter(date1) || tempDate.isEqual(date1)) && (tempDate.isEqual(date2) || tempDate.isBefore(date2))) {
                printOrderToString(tempDate, localDateTimeHashMapEntry.getValue());
            }
        }
    }

    public void printClosestDate(LocalDateTime date) {
        System.out.println("------------Closest Order to Date------------");
        long dif = Long.MAX_VALUE;
        LocalDateTime answerDate = null;
        HashMap<Integer, Integer> answerHash = null;
        for (Map.Entry<LocalDateTime, HashMap<Integer, Integer>> localDateTimeHashMapEntry : container.entrySet()) {
            long tempDif = Math.abs(date.toEpochSecond(ZoneOffset.UTC) - localDateTimeHashMapEntry.getKey().toEpochSecond(ZoneOffset.UTC));
            if (tempDif < dif) {
                dif = tempDif;
                answerDate = localDateTimeHashMapEntry.getKey();
                answerHash = localDateTimeHashMapEntry.getValue();
            }
        }
        printOrderToString(answerDate, answerHash);
    }

    private void printOrderToString(LocalDateTime date, HashMap<Integer, Integer> hashMap) {
        System.out.println("Date = " + date + "\nGoods :\n" + hashToString(hashMap));
        System.out.println("-----------------------------------------");
    }

    private String hashToString(HashMap<Integer, Integer> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        hashMap.forEach((key, value) -> stringBuilder.append("Product ID = ").append(key).append(" Amount = ").append(value).append('\n'));
        return stringBuilder.toString().strip();
    }

}
