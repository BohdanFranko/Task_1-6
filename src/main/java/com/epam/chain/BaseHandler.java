package com.epam.chain;

import com.epam.file.MyFileReader;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

public class BaseHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(List<File> files) {
        if (next != null) {
            next.handle(files);
        }
    }

    @Override
    public void print(List<File> files, Predicate<File> predicate) {
        for (File file : files) {
            if (file.isFile() && predicate.test(file)) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                print(List.of(Objects.requireNonNull(file.listFiles())), predicate);
            }
        }
    }

}
