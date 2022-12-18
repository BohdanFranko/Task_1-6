package com.epam.chain;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

public class FileByExtensionHandler extends BaseHandler {
    @Override
    public void handle(List<File> files) {
        System.out.println("Find files by fileExtension 1/0");
        Inputer inputer = new Inputer();
        if (inputer.input()) {
            String extension = inputer.inputExtension();
            Predicate<File> predicate = file -> extension(file.getName()).equals(extension);
            print(files, predicate);
        } else {
            super.handle(files);
        }
    }

    private String extension(String text) {
        return text.substring(text.lastIndexOf('.') + 1);
    }
}
