package com.epam.command.store;

import com.epam.store.StoreManager;

import java.util.Scanner;

public interface StoreCommand {
    public void execute(Scanner scanner, StoreManager storeManager);
}
