package com.epam.main;

import com.epam.store.StoreManager;
import com.epam.validator.DateValidator;
import com.epam.good.Good;

import java.util.*;

public class DemoStore {
    public static void main(String[] args) {
        List<Good> list = new ArrayList<>();
        list.add(new Good("First", 20));
        list.add(new Good("Second", 25));
        list.add(new Good("Third", 15));
        StoreManager storeManager = new StoreManager(list);

        Scanner scanner = new Scanner(System.in);

        DateValidator validator = new DateValidator();
        int year1;
        int year2;
        int month1;
        int month2;
        int day1;
        int day2;
        int hrs1;
        int hrs2;
        int min1;
        int min2;
        int temp = -1;

        while (temp != 0) {
            storeManager.printInfo();
            temp = scanner.nextInt();
            int index = -1;
            switch (temp) {
                case 1 -> storeManager.printGoods();
                case 2 -> {
                    while (!storeManager.validateIndex(index)) {
                        System.out.print("Enter index: ");
                        index = scanner.nextInt();
                    }
                    storeManager.addGoodToBucket(index);
                }
                case 3 -> storeManager.printBucket();
                case 4 -> System.out.println("Total Price = " + storeManager.buyAllFromBucket());
                case 5 -> storeManager.printLastFive();
                case 6 -> storeManager.printAllOrders();
                case 7 -> {
                    do {
                        System.out.print("Enter year of first date: ");
                        year1 = scanner.nextInt();
                    } while (!validator.validatePositiveNumber(year1));
                    do {
                        System.out.print("Enter month of first date: ");
                        month1 = scanner.nextInt();
                    } while (!validator.validateMonth(month1));
                    do {
                        System.out.print("Enter day of first date: ");
                        day1 = scanner.nextInt();
                    } while (!validator.validateDay(day1));
                    do {
                        System.out.print("Enter hour of first date: ");
                        hrs1 = scanner.nextInt();
                    } while (!validator.validateHour(hrs1));
                    do {
                        System.out.print("Enter min of first date: ");
                        min1 = scanner.nextInt();
                    } while (!validator.validateMinute(min1));
                    do {
                        System.out.print("Enter year of second date: ");
                        year2 = scanner.nextInt();
                    } while (!validator.validatePositiveNumber(year2));
                    do {
                        System.out.print("Enter month of second date: ");
                        month2 = scanner.nextInt();
                    } while (!validator.validateMonth(month2));
                    do {
                        System.out.print("Enter day of second date: ");
                        day2 = scanner.nextInt();
                    } while (!validator.validateDay(day2));
                    do {
                        System.out.print("Enter hour of second date: ");
                        hrs2 = scanner.nextInt();
                    } while (!validator.validateHour(hrs2));
                    do {
                        System.out.print("Enter min of second date: ");
                        min2 = scanner.nextInt();
                    } while (!validator.validateMinute(min2));
                    storeManager.printAllOrdersFromDateToDate(year1, month1, day1, hrs1, min1, year2, month2, day2, hrs2, min2);
                }
                case 8 -> {
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter month: ");
                    int month = scanner.nextInt();
                    System.out.print("Enter day: ");
                    int day = scanner.nextInt();
                    System.out.print("Enter hour: ");
                    int hrs = scanner.nextInt();
                    System.out.print("Enter min: ");
                    int min = scanner.nextInt();
                    storeManager.printClosestDateOrder(year, month, day, hrs, min);
                }
            }
        }
    }
}
