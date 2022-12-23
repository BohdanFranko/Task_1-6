package com.epam.command.serialize;

import com.epam.container.TransportList;

import java.util.Scanner;

public interface SerializeCommand {
    public void execute(Scanner scanner, TransportList<com.epam.transport.Automobile> transportList);
}
