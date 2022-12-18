package com.epam.command.store;

import com.epam.store.StoreManager;

import java.util.Scanner;

public class BuyAllFromBucketCommand implements StoreCommand {
    @Override
    public void execute(Scanner scanner, StoreManager storeManager) {
        System.out.println("Total Price = " + storeManager.buyAllFromBucket());
    }
}
