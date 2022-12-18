package com.epam.validator;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DateValidator {
    public boolean validatePositiveNumber(int number) {
        return number >= 0;
    }

    public boolean validateMonth(int month) {
        return month >= 0 && month < 12;
    }

    public boolean validateDay(int year, int month, int day) {
        LocalDateTime date = LocalDateTime.of(year, month, 1, 1, 1);
        date = date.minusDays(1);
        date = date.plusDays(day);
        return date.getMonthValue() == month;
    }

    public boolean validateHour(int hour) {
        return hour >= 0 && hour < 24;
    }

    public boolean validateMinute(int min) {
        return min >= 0 && min < 60;
    }
}
