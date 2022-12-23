package com.epam.command.serialize;

import com.epam.container.TransportList;
import com.epam.transport.Automobile;

import java.util.Scanner;

public class PrintCatalogueCommand implements SerializeCommand {
    @Override
    public void execute(Scanner scanner, TransportList<Automobile> transportList) {
        System.out.println("---------------Goods--------------");
        transportList.forEach(System.out::println);
    }
}
