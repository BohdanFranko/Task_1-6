package com.epam.main;

import com.epam.store.StoreManager;
import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import com.epam.validator.DateValidator;
import com.epam.validator.SwitcherValidator;

import java.time.LocalDateTime;
import java.util.*;

public class DemoStore {
    public static void main(String[] args) {
        List<Automobile> list = new ArrayList<>();
        list.add(new Automobile(200, 4, VehicleType.LAND, "Mazeratti", 20));
        list.add(new Automobile(250, 4, VehicleType.LAND, "Lexus", 40));
        list.add(new Automobile(180, 4, VehicleType.LAND, "Lada", 50));
        StoreManager storeManager = new StoreManager(list);

        Scanner scanner = new Scanner(System.in);

        DateValidator dateValidator = new DateValidator();
        SwitcherValidator switcherValidator = new SwitcherValidator();
        String switcher = "a";
        while (!switcher.equals("0")) {
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
            do {
                switcher = scanner.nextLine();
                if (!switcherValidator.validate(switcher)) {
                    System.out.println("Must be a number from 0 to 9");
                }
            } while (!switcherValidator.validate(switcher));
            int index = -1;
            int year;
            int month;
            int day;
            int hours;
            int min;
            switch (switcher) {
                case "1" -> storeManager.printGoods();
                case "2" -> {
                    System.out.print("Enter product ID: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    storeManager.addGoodToBucket(index);
                }
                case "3" -> storeManager.printBucket();
                case "4" -> System.out.println("Total Price = " + storeManager.buyAllFromBucket());
                case "5" -> storeManager.printLastFive();
                case "6" -> storeManager.printAllOrders();
                case "7" -> {
                    do {
                        System.out.print("Enter year: ");
                        year = scanner.nextInt();
                        if (!dateValidator.validatePositiveNumber(year)) {
                            System.out.println("Year must be a positive number");
                        }
                    } while (!dateValidator.validatePositiveNumber(year));
                    do {
                        System.out.print("Enter month: ");
                        month = scanner.nextInt();
                        if (!dateValidator.validateMonth(month)) {
                            System.out.println("month must be from 0 to 11");
                        }
                    } while (!dateValidator.validateMonth(month));
                    do {
                        System.out.print("Enter dayOfMonth: ");
                        day = scanner.nextInt();
                        if (!dateValidator.validateDay(year, month, day)) {
                            System.out.println("incorrect day");
                        }
                    } while (!dateValidator.validateDay(year, month, day));
                    do {
                        System.out.print("Enter hour: ");
                        hours = scanner.nextInt();
                        if (!dateValidator.validateHour(hours)) {
                            System.out.println("hour must be >= 0 and < 24");
                        }
                    } while (!dateValidator.validateHour(hours));
                    do {
                        System.out.print("Enter minute: ");
                        min = scanner.nextInt();
                        if (!dateValidator.validateMinute(min)) {
                            System.out.println("minute must be >=0 and < 60");
                        }
                    } while (!dateValidator.validateMinute(min));
                    LocalDateTime date1 = LocalDateTime.of(year, month, day, hours, min);
                    do {
                        System.out.print("Enter year: ");
                        year = scanner.nextInt();
                        if (!dateValidator.validatePositiveNumber(year)) {
                            System.out.println("Year must be a positive number");
                        }
                    } while (!dateValidator.validatePositiveNumber(year));
                    do {
                        System.out.print("Enter month: ");
                        month = scanner.nextInt();
                        if (!dateValidator.validateMonth(month)) {
                            System.out.println("month must be from 0 to 11");
                        }
                    } while (!dateValidator.validateMonth(month));
                    do {
                        System.out.print("Enter dayOfMonth: ");
                        day = scanner.nextInt();
                        if (!dateValidator.validateDay(year, month, day)) {
                            System.out.println("incorrect day");
                        }
                    } while (!dateValidator.validateDay(year, month, day));
                    do {
                        System.out.print("Enter hour: ");
                        hours = scanner.nextInt();
                        if (!dateValidator.validateHour(hours)) {
                            System.out.println("hour must be >= 0 and < 24");
                        }
                    } while (!dateValidator.validateHour(hours));
                    do {
                        System.out.print("Enter minute: ");
                        min = scanner.nextInt();
                        if (!dateValidator.validateMinute(min)) {
                            System.out.println("minute must be >=0 and < 60");
                        }
                    } while (!dateValidator.validateMinute(min));
                    scanner.nextLine();
                    storeManager.printAllOrdersFromDateToDate(date1, LocalDateTime.of(year, month, day, hours, min));
                }
                case "8" -> {
                    do {
                        System.out.print("Enter year: ");
                        year = scanner.nextInt();
                        if (!dateValidator.validatePositiveNumber(year)) {
                            System.out.println("Year must be a positive number");
                        }
                    } while (!dateValidator.validatePositiveNumber(year));
                    do {
                        System.out.print("Enter month: ");
                        month = scanner.nextInt();
                        if (!dateValidator.validateMonth(month)) {
                            System.out.println("month must be from 0 to 11");
                        }
                    } while (!dateValidator.validateMonth(month));
                    do {
                        System.out.print("Enter dayOfMonth: ");
                        day = scanner.nextInt();
                        if (!dateValidator.validateDay(year, month, day)) {
                            System.out.println("incorrect day");
                        }
                    } while (!dateValidator.validateDay(year, month, day));
                    do {
                        System.out.print("Enter hour: ");
                        hours = scanner.nextInt();
                        if (!dateValidator.validateHour(hours)) {
                            System.out.println("hour must be >= 0 and < 24");
                        }
                    } while (!dateValidator.validateHour(hours));
                    do {
                        System.out.print("Enter minute: ");
                        min = scanner.nextInt();
                        if (!dateValidator.validateMinute(min)) {
                            System.out.println("minute must be >=0 and < 60");
                        }
                    } while (!dateValidator.validateMinute(min));
                    scanner.nextLine();
                    storeManager.printClosestDateOrder(LocalDateTime.of(year, month, day, hours, min));
                }
            }
        }
    }
}
