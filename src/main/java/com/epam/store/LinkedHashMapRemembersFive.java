package com.epam.store;

import com.epam.transport.Automobile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;

public class LinkedHashMapRemembersFive {
    private LinkedHashMap<Integer, Integer> linkedHashMap;

    public LinkedHashMapRemembersFive() {
        linkedHashMap = new LinkedHashMap<>();
    }

    public void add(int productId) {
        linkedHashMap.put(linkedHashMap.size(), productId);
    }

    public void print() {
        System.out.println("------------Last Five added to Bucket------------");
        final int numberToPrint = 5;
        List<Integer> listKeys = new ArrayList<>(linkedHashMap.values());
        ListIterator<Integer> iterator = listKeys.listIterator(listKeys.size());
        for (int i = 0; i < numberToPrint; i++) {
            if (iterator.hasPrevious()) {
                System.out.println("ProductID = " + iterator.previous());
            } else break;
        }
    }
}
