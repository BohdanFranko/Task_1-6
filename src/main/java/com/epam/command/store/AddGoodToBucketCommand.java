package com.epam.command.store;

import com.epam.store.StoreManager;

import java.util.Scanner;

public class AddGoodToBucketCommand implements StoreCommand {
    @Override
    public void execute(Scanner scanner, StoreManager storeManager) {
        System.out.print("Enter product ID: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        storeManager.addGoodToBucket(index);
    }
}
