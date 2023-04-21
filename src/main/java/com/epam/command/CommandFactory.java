package com.epam.command;

import com.epam.command.serialize.*;
import com.epam.command.store.*;
import com.epam.command.store.DefaultCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory<T extends Command> {
    private final Map<String, T> commands = new HashMap<>();

    public void add(String name, T command) {
        commands.put(name, command);
    }

    public void defaultStore(CommandFactory<StoreCommand> com) {
        com.add(StoreCommandName.PRINT_ALL_GOODS, new PrintAllGoodsCommand());
        com.add(StoreCommandName.ADD_TO_BUCKET, new AddGoodToBucketCommand());
        com.add(StoreCommandName.PRINT_BUCKET, new PrintBucketCommand());
        com.add(StoreCommandName.PRINT_LAST_FIVE, new PrintLastFiveCommand());
        com.add(StoreCommandName.PRINT_ORDERS, new PrintAllOrdersCommand());
        com.add(StoreCommandName.DATE_TO_DATE, new DateToDateCommand());
        com.add(StoreCommandName.CLOSEST_DATE, new ClosestDateCommand());
        com.add(StoreCommandName.DEFAULT_COMMAND, new DefaultCommand());
        com.add(StoreCommandName.ADD_ALL, new BuyAllFromBucketCommand());
    }

    public void defaultSerialize(CommandFactory<SerializeCommand> com) {
        com.add(SerializeCommandName.ADD_GOOD_AUTO, new AddGoodAutoCommand());
        com.add(SerializeCommandName.ADD_GOOD_MANUALLY, new AddGoodManuallyCommand());
        com.add(SerializeCommandName.PRINT_GOODS, new PrintCatalogueCommand());
        com.add(SerializeCommandName.DEFAULT_COMMAND, new com.epam.command.serialize.DefaultCommand());
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
