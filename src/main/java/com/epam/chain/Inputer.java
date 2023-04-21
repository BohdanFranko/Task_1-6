package com.epam.chain;

import java.util.Scanner;

public class Inputer {
    private Scanner scanner;

    public Inputer() {
        scanner = new Scanner(System.in);
    }

    public boolean input() {
        System.out.println("1 - yes, 0 - no");
        String switcher;
        do {
            switcher = scanner.nextLine();
            if (switcher.length() != 1 || switcher.charAt(0) > '1' || switcher.charAt(0) < '0') {
                System.out.println("Input only 1 or 0");
            }
        } while (switcher.length() != 1 || switcher.charAt(0) > '1' || switcher.charAt(0) < '0');
        return switch (switcher) {
            case "0" -> false;
            case "1" -> true;
            default -> false;
        };
    }

    public String inputName() {
        System.out.println("Input file name");
        return scanner.nextLine();
    }

    public String inputExtension() {
        System.out.println("Input extension");
        return scanner.nextLine();
    }

    public Long inputSize() {
        System.out.println("Input size:");
        return scanner.nextLong();
    }

    public boolean InputTypeOfAdd() {
        System.out.println("1 - Auto, 0 - Manually");
        String type;
        do {
            type = scanner.nextLine();
            if (type.length() != 1 || type.charAt(0) > '1' || type.charAt(0) < '0') {
                System.out.println("Input only 1 or 0");
            }
        } while (type.length() != 1 || type.charAt(0) > '1' || type.charAt(0) < '0');
        return switch (type) {
            case "1" -> true;
            default -> false;
        };
    }
}
