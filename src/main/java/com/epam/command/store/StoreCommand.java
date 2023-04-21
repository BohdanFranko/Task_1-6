package com.epam.command.store;

import com.epam.command.Command;
import com.epam.store.StoreManager;

import java.util.Scanner;

public interface StoreCommand extends Command {
    public void execute(Scanner scanner, StoreManager storeManager);
}
