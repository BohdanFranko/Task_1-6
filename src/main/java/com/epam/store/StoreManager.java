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
        bucket.add(autoId);
        lastAdd.add(autoId);
    }

    public void printBucket() {
        bucket.print();
    }

    public int buyAllFromBucket() {
        int totalPrice = bucket.totalPrice(goods);
        orders.add(LocalDateTime.now(), new HashMap<>(bucket.getContainer()));
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

    public boolean validateIndex(int index) {
        return index >= 0 && index <= goods.getGoods().size();
    }

    public void printInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("Enter 1 to print all goods");
        System.out.println("Enter 2 to add good to the bucket by ID");
        System.out.println("Enter 3 to print your current bucket");
        System.out.println("Enter 4 to buy all goods from your current bucket");
        System.out.println("Enter 5 to print five last good added to bucket");
        System.out.println("Enter 6 to print all orders");
        System.out.println("Enter 7 to print all orders from Date1 to Date2");
        System.out.println("Enter 8 to print the order with the closest Date");
        System.out.println("Enter 0 to exit program");
        System.out.println("--------------------------------------------------");
    }

}
