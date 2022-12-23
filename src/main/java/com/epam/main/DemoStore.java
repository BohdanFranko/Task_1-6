package com.epam.main;

import com.epam.command.CommandFactory;
import com.epam.command.store.StoreCommand;
import com.epam.command.store.StoreCommandName;
import com.epam.store.StoreManager;
import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import com.epam.validator.SwitcherValidator;

import java.util.*;
import java.util.function.Consumer;

public class DemoStore {
    public static void main(String[] args) {
        List<Automobile> list = new ArrayList<>();
        list.add(new Automobile(200, 4, VehicleType.LAND, "Mazeratti", 20));
        list.add(new Automobile(250, 4, VehicleType.LAND, "Lexus", 40));
        list.add(new Automobile(180, 4, VehicleType.LAND, "Lada", 50));
        StoreManager storeManager = new StoreManager(list);

        Scanner scanner = new Scanner(System.in);

        CommandFactory<StoreCommand> commandFactory = CommandFactory.getInstance();
        commandFactory.defaultStore();

        SwitcherValidator switcherValidator = new SwitcherValidator();
        String switcher = "a";
        while (!switcher.equals("0")) {
            System.out.println("--------------------------------------------------");
            System.out.println("Enter 1 to print all goods");
            System.out.println("Enter 2 to add good to the bucket by ID");
            System.out.println("Enter 3 to print your current bucket");
            System.out.println("Enter 4 to buy all goods from your current bucket");
            System.out.println("Enter 5 to print five last good added to bucket");
            System.out.println("Enter 6 to print all orders");
            System.out.println("Enter 7 to print all orders from Date1 to Date2");
            System.out.println("Enter 8 to print the order with the closest Date");
            System.out.println("Enter 0 to exit program");
            System.out.println("--------------------------------------------------");
            Map<String, Consumer<String>> saleStrategy = new HashMap<>();
            saleStrategy.put("1", str -> commandFactory.getCommand(StoreCommandName.PRINT_ALL_GOODS).execute(scanner, storeManager));
            saleStrategy.put("2", str -> commandFactory.getCommand(StoreCommandName.ADD_TO_BUCKET).execute(scanner, storeManager));
            saleStrategy.put("3", str -> commandFactory.getCommand(StoreCommandName.PRINT_BUCKET).execute(scanner, storeManager));
            saleStrategy.put("4", str -> commandFactory.getCommand(StoreCommandName.ADD_ALL).execute(scanner, storeManager));
            saleStrategy.put("5", str -> commandFactory.getCommand(StoreCommandName.PRINT_LAST_FIVE).execute(scanner, storeManager));
            saleStrategy.put("6", str -> commandFactory.getCommand(StoreCommandName.PRINT_ORDERS).execute(scanner, storeManager));
            saleStrategy.put("7", str -> commandFactory.getCommand(StoreCommandName.DATE_TO_DATE).execute(scanner, storeManager));
            saleStrategy.put("8", str -> commandFactory.getCommand(StoreCommandName.CLOSEST_DATE).execute(scanner, storeManager));
            saleStrategy.put("0", str -> commandFactory.getCommand(StoreCommandName.DEFAULT_COMMAND).execute(scanner, storeManager));

            do {
                switcher = scanner.nextLine();
                if (!switcherValidator.validate(switcher)) {
                    System.out.println("Must be a number from 0 to 9");
                }
                saleStrategy.get(switcher).accept("");
            } while (!switcherValidator.validate(switcher));

        }
    }
}
