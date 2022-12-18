package com.epam.command.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StoreCommandFactory {
    private static final Map<String, StoreCommand> commands = new HashMap<>();

    private StoreCommandFactory() {
        commands.put(StoreCommandName.PRINT_ALL_GOODS, new PrintAllGoodsCommand());
        commands.put(StoreCommandName.ADD_TO_BUCKET, new AddGoodToBucketCommand());
        commands.put(StoreCommandName.PRINT_BUCKET, new PrintBucketCommand());
        commands.put(StoreCommandName.PRINT_LAST_FIVE, new PrintLastFiveCommand());
        commands.put(StoreCommandName.PRINT_ORDERS, new PrintAllOrdersCommand());
        commands.put(StoreCommandName.DATE_TO_DATE, new DateToDateCommand());
        commands.put(StoreCommandName.CLOSEST_DATE, new ClosestDateCommand());
        commands.put(StoreCommandName.DEFAULT_COMMAND, new DefaultCommand());
        commands.put(StoreCommandName.ADD_ALL, new BuyAllFromBucketCommand());
    }

    public static StoreCommandFactory getInstance() {
        return Holder.INSTANCE;
    }

    public StoreCommand getCommand(String name) {
        return Optional.ofNullable(commands.get(name)).orElse(commands.get(StoreCommandName.DEFAULT_COMMAND));
    }

    private static class Holder {
        private static final StoreCommandFactory INSTANCE = new StoreCommandFactory();
    }

}
