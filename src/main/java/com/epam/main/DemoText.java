package com.epam.main;

import com.epam.chain.FileByDateOfChangeHandler;
import com.epam.chain.FileByExtensionHandler;
import com.epam.chain.FileByNameHandler;
import com.epam.chain.FileBySizeHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DemoText {
    public static void main(String[] args) {
        String dirPath = "D:\\Testic";
        File dir = new File(dirPath);
        List<File> lst = new ArrayList<>(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
        FileByNameHandler handler1 = new FileByNameHandler();
        FileByExtensionHandler handler2 = new FileByExtensionHandler();
        FileBySizeHandler handler3 = new FileBySizeHandler();
        FileByDateOfChangeHandler handler4 = new FileByDateOfChangeHandler();
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        handler3.setNext(handler4);
        handler1.handle(lst);

    }
}
