package com.epam.store;

import com.epam.transport.Automobile;

import java.time.LocalDateTime;
import java.util.*;

public class StoreManager {
    private ListOfGoods goods;
    private Bucket bucket;
    private LinkedHashMapRemembersFive lastAdd;
    private Orders orders;

    public StoreManager(List<Automobile> collection) {
        goods = new ListOfGoods(collection);
        bucket = new Bucket();
        lastAdd = new LinkedHashMapRemembersFive();
        orders = new Orders();
    }

    public void printGoods() {
        goods.print();
    }

    public void addGoodToBucket(int autoId) {
        bucket.add(autoId, goods);
        lastAdd.add(autoId);
    }

    public void printBucket() {
        bucket.print();
    }

    public int buyAllFromBucket() {
        int totalPrice = bucket.totalPrice(goods);
        orders.add(LocalDateTime.now(), new HashMap<>(bucket.automobileIntegerHashMap(goods)));
        bucket.clear();
        return totalPrice;
    }

    public void printLastFive() {
        lastAdd.print();
    }

    public void printAllOrders() {
        orders.print();
    }

    public void printAllOrdersFromDateToDate(LocalDateTime date1, LocalDateTime date2) {
        orders.printInDateRange(date1, date2);
    }

    public void printClosestDateOrder(LocalDateTime dateTime) {
        orders.printClosestDate(dateTime);
    }

}
