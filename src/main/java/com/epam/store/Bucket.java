package com.epam.store;

import com.epam.transport.Automobile;

import java.util.HashMap;
import java.util.Map;

public class Bucket {
    private HashMap<Integer, Integer> container;

    public HashMap<Integer, Integer> getContainer() {
        return container;
    }

    public Bucket() {
        container = new HashMap<>();
    }

    public boolean add(int productId, ListOfGoods list) {
        if (!list.checkId(productId)) {
            System.out.println("A product with that id doesn't exist");
            return false;
        }
        if (container.containsKey(productId)) {
            container.put(productId, container.get(productId) + 1);
        } else {
            container.put(productId, 1);
        }
        return true;
    }

    public void print() {
        System.out.println("------------Bucket------------");
        container.forEach((key, value) -> System.out.println("ID = " + key + " Amount = " + value));
    }

    public int totalPrice(ListOfGoods list) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : container.entrySet()) {
            for (Automobile good : list.getGoods()) {
                if (good.getId() == integerIntegerEntry.getKey()) {
                    sum += good.getPrice() * integerIntegerEntry.getValue();
                }
            }
        }
        return sum;
    }

    public HashMap<Automobile, Integer> automobileIntegerHashMap(ListOfGoods list) {
        HashMap<Automobile, Integer> hashMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> integerIntegerEntry : container.entrySet()) {
            hashMap.put(list.getAutoById(integerIntegerEntry.getKey()), integerIntegerEntry.getValue());
        }
        return hashMap;
    }

    public void clear() {
        container.clear();
    }
}
