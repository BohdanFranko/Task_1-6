package com.epam.main;

import com.epam.chain.Inputer;
import com.epam.command.CommandFactory;
import com.epam.command.serialize.SerializeCommand;
import com.epam.command.serialize.SerializeCommandName;
import com.epam.container.TransportList;
import com.epam.file.GoodsSerialize;
import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class DemoSerialize {
    public static void main(String[] args) {
        final String fileName = "D:\\Testic\\goods";
        CommandFactory<SerializeCommand> commandFactory = CommandFactory.getInstance();
        commandFactory.defaultSerialize(commandFactory);
        GoodsSerialize<Automobile> goodsSerialize = new GoodsSerialize<>(fileName);
        Scanner scanner = new Scanner(System.in);
        String switcher;

        TransportList<Automobile> list = goodsSerialize.read();

        Inputer inputer = new Inputer();
        boolean type = inputer.InputTypeOfAdd();
        Map<String, Consumer<String>> saleStrategy = new HashMap<>();
        saleStrategy.put("1", str -> commandFactory.getCommand(SerializeCommandName.PRINT_GOODS).execute(scanner, list));
        if (type) {
            saleStrategy.put("2", str -> commandFactory.getCommand(SerializeCommandName.ADD_GOOD_AUTO).execute(scanner, list));
        } else {
            saleStrategy.put("2", str -> commandFactory.getCommand(SerializeCommandName.ADD_GOOD_MANUALLY).execute(scanner, list));
        }
        saleStrategy.put("0", str -> commandFactory.getCommand(SerializeCommandName.DEFAULT_COMMAND).execute(scanner, list));
        do {
            System.out.println("Type 1 to print goods");
            System.out.println("Type 2 to add good");
            System.out.println("Type 0 to exit and save");
            switcher = scanner.nextLine();

            saleStrategy.get(switcher).accept("");
        } while (!switcher.equals("0"));
        goodsSerialize.save(list);
    }
}
