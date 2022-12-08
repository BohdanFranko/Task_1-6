package com.epam.container.store;

import com.epam.good.Good;

import java.util.*;

public class StoreManager {
    private List<Good> goods;
    private HashMap<Good, Integer> bucket;
    private LinkedHashMap<Integer, Good> lastAdd;
    private TreeMap<Date, HashMap<Good, Integer>> orders;

    public StoreManager(Collection<Good> collection) {
        goods = new ArrayList<>(collection);
        bucket = new HashMap<>();
        lastAdd = new LinkedHashMap<>();
        orders = new TreeMap<>();
    }

    public void printGoods() {
        System.out.println("------------Goods------------");
        goods.forEach(System.out::println);
    }

    public void addGoodToBucket(int index) {
        if (!bucket.containsKey(goods.get(index))) {
            bucket.put(goods.get(index), 1);
        } else {
            bucket.put(goods.get(index), bucket.get(goods.get(index)) + 1);
        }
        lastAdd.put(lastAdd.size(), goods.get(index));
    }

    public void printBucket() {
        System.out.println("------------Bucket------------");
        for (Map.Entry<Good, Integer> goodEntry : bucket.entrySet()) {
            System.out.println(goodEntry.getKey() + " Amount = " + goodEntry.getValue());
        }
    }

    public int buyAllFromBucket() {
        int sum = 0;
        for (Map.Entry<Good, Integer> goodEntry : bucket.entrySet()) {
            sum += goodEntry.getKey().getPrice() * goodEntry.getValue();
        }
        orders.put(new Date(), new HashMap<>(bucket));
        bucket.clear();
        return sum;
    }

    public void printLastFive() {
        System.out.println("------------Last Five------------");
        final int numberToPrint = 5;
        List<Good> listKeys = new ArrayList<>(lastAdd.values());
        ListIterator<Good> iterator = listKeys.listIterator(listKeys.size());
        for (int i = 0; i < numberToPrint; i++) {
            if (iterator.hasPrevious()) {
                System.out.println(iterator.previous());
            } else break;
        }
    }

    public void printAllOrders() {
        System.out.println("------------All Orders------------");
        for (Map.Entry<Date, HashMap<Good, Integer>> dateHashMapEntry : orders.entrySet()) {
            printOrders(dateHashMapEntry.getKey(), dateHashMapEntry.getValue());
        }
    }

    public void printAllOrdersFromDateToDate(int year1, int month1, int day1, int hrs1, int min1,
                                             int year2, int month2, int day2, int hrs2, int min2) {
        System.out.println("------------From Date1 to Date2------------");
        Date first = setDate(year1, month1, day1, hrs1, min1);
        Date second = setDate(year2, month2, day2, hrs2, min2);
        if (first.getTime() > second.getTime()) {
            long temp = first.getTime();
            first = second;
            second = new Date(temp);
            System.out.println(first);
            System.out.println(second);
        }
        HashMap<Date, HashMap<Good, Integer>> orders = new HashMap<>();
        for (Map.Entry<Date, HashMap<Good, Integer>> dateCollectionEntry : this.orders.entrySet()) {
            long date = dateCollectionEntry.getKey().getTime();
            if (date >= first.getTime() && date < +second.getTime()) {
                orders.put(dateCollectionEntry.getKey(), dateCollectionEntry.getValue());
            }
        }
        for (Map.Entry<Date, HashMap<Good, Integer>> dateCollectionEntry : orders.entrySet()) {
            printOrders(dateCollectionEntry.getKey(), dateCollectionEntry.getValue());
        }
    }

    public void printClosestDateOrder(int year, int month, int day, int hrs, int min) {
        System.out.println("------------Closest Order to Date------------");
        Date date = setDate(year, month, day, hrs, min);
        long minDif = Long.MAX_VALUE;
        HashMap<Good, Integer> order = null;
        Date dateAns = new Date();
        for (Map.Entry<Date, HashMap<Good, Integer>> dateCollectionEntry : orders.entrySet()) {
            if (Math.abs(dateCollectionEntry.getKey().getTime() - date.getTime()) < minDif) {
                order = dateCollectionEntry.getValue();
                dateAns = dateCollectionEntry.getKey();
                minDif = Math.abs(dateCollectionEntry.getKey().getTime() - date.getTime());
            }
        }
        printOrders(dateAns, order);
    }

    public boolean validateIndex(int index) {
        return index >= 0 && index < goods.size();
    }

    public void printInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("Enter 1 to print all goods");
        System.out.println("Enter 2 to add good to the bucket");
        System.out.println("Enter 3 to print your current bucket");
        System.out.println("Enter 4 to buy all goods from your current bucket");
        System.out.println("Enter 5 to print five last good added to bucket");
        System.out.println("Enter 6 to print all orders");
        System.out.println("Enter 7 to print all orders from Date1 to Date2");
        System.out.println("Enter 8 to print the order with the closest Date");
        System.out.println("Enter 0 to exit program");
        System.out.println("--------------------------------------------------");
    }

    private void printOrders(Date date, HashMap<Good, Integer> collection) {
        System.out.println("Date = " + date);
        System.out.println("Goods :");
        for (Map.Entry<Good, Integer> goodEntry : collection.entrySet()) {
            System.out.println(goodEntry.getKey() + " Amount = " + goodEntry.getValue());
        }
        System.out.println("-----------------------------------------");
    }

    private Date setDate(int year, int month, int day, int hrs, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hrs);
        calendar.set(Calendar.MINUTE, min);
        return calendar.getTime();
    }
}
