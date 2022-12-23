package com.epam.command.store;

import com.epam.date.DateInputer;
import com.epam.store.StoreManager;

import java.util.Scanner;

public class ClosestDateCommand implements StoreCommand {
    @Override
    public void execute(Scanner scanner, StoreManager storeManager) {
        DateInputer dateInputer = new DateInputer();
        storeManager.printClosestDateOrder(dateInputer.date());
    }
}
