package com.epam.date;

import com.epam.validator.DateValidator;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DateInputer {
    public LocalDateTime date() {
        Scanner scanner = new Scanner(System.in);
        DateValidator dateValidator = new DateValidator();
        int year;
        int month;
        int day;
        int hours;
        int min;
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
        return LocalDateTime.of(year, month, day, hours, min);
    }
}
