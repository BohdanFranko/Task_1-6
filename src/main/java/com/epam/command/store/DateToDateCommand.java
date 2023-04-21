package com.epam.command.store;

import com.epam.date.DateInputer;
import com.epam.store.StoreManager;

import java.util.Scanner;

public class DateToDateCommand implements StoreCommand {
    @Override
    public void execute(Scanner scanner, StoreManager storeManager) {
        DateInputer dateInputer = new DateInputer();
        storeManager.printAllOrdersFromDateToDate(dateInputer.date(), dateInputer.date());
    }
}
