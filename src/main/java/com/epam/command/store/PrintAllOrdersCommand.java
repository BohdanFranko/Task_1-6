package com.epam.command.store;

import com.epam.store.StoreManager;

import java.util.Scanner;

public class PrintAllOrdersCommand implements StoreCommand {
    @Override
    public void execute(Scanner scanner, StoreManager storeManager) {
        storeManager.printAllOrders();
    }
}
