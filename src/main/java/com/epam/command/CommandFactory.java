package com.epam.command;

import com.epam.command.serialize.AddGoodAutoCommand;
import com.epam.command.serialize.AddGoodManuallyCommand;
import com.epam.command.serialize.PrintCatalogueCommand;
import com.epam.command.serialize.SerializeCommandName;
import com.epam.command.store.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory<T> {
    private final Map<String, T> commands = new HashMap<>();

    public void add(String name, T command) {
        commands.put(name, command);
    }

    public void defaultStore() {
        commands.put(StoreCommandName.PRINT_ALL_GOODS, (T) new PrintAllGoodsCommand());
        commands.put(StoreCommandName.ADD_TO_BUCKET, (T) new AddGoodToBucketCommand());
        commands.put(StoreCommandName.PRINT_BUCKET, (T) new PrintBucketCommand());
        commands.put(StoreCommandName.PRINT_LAST_FIVE, (T) new PrintLastFiveCommand());
        commands.put(StoreCommandName.PRINT_ORDERS, (T) new PrintAllOrdersCommand());
        commands.put(StoreCommandName.DATE_TO_DATE, (T) new DateToDateCommand());
        commands.put(StoreCommandName.CLOSEST_DATE, (T) new ClosestDateCommand());
        commands.put(StoreCommandName.DEFAULT_COMMAND, (T) new DefaultCommand());
        commands.put(StoreCommandName.ADD_ALL, (T) new BuyAllFromBucketCommand());
    }

    public void defaultSerialize() {
        commands.put(SerializeCommandName.ADD_GOOD_AUTO, (T) new AddGoodAutoCommand());
        commands.put(SerializeCommandName.ADD_GOOD_MANUALLY, (T) new AddGoodManuallyCommand());
        commands.put(SerializeCommandName.PRINT_GOODS, (T) new PrintCatalogueCommand());
        commands.put(SerializeCommandName.DEFAULT_COMMAND, (T) new com.epam.command.serialize.DefaultCommand());
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }

    public T getCommand(String name) {
        return Optional.ofNullable(commands.get(name)).orElse(commands.get(StoreCommandName.DEFAULT_COMMAND));
    }

    private static class Holder {
        private static final CommandFactory INSTANCE = new CommandFactory();
    }

}
