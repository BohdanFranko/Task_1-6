package com.epam.validator;

import java.util.Calendar;

public class DateValidator {
    public boolean validatePositiveNumber(int number) {
        return number >= 0;
    }

    public boolean validateMonth(int month) {
        return month >= 0 && month < 12;
    }

    public boolean validateDay(int day) {
        return day >= 0 && day < 32;
    }

    public boolean validateHour(int hour) {
        return hour >= 0 && hour <= 24;
    }

    public boolean validateMinute(int min) {
        return min >= 0 && min <= 60;
    }
}
