package com.epam.chain;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

public class FileByNameHandler extends BaseHandler {
    @Override
    public void handle(List<File> files) {
        System.out.println("Find files by fileName 1/0");
        Inputer inputer = new Inputer();
        if (inputer.input()) {
            String name = inputer.inputName();
            Predicate<File> predicate = file -> withOutExtension(file.getName()).equals(name);
            print(files, predicate);
        } else {
            super.handle(files);
        }
    }

    private String withOutExtension(String text) {
        return text.substring(0, text.lastIndexOf('.'));
    }
}
