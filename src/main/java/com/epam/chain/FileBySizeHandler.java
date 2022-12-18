package com.epam.chain;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;

public class FileBySizeHandler extends BaseHandler {
    @Override
    public void handle(List<File> files) {
        System.out.println("Find files by fileSize 1/0");
        Inputer inputer = new Inputer();
        if (inputer.input()) {
            Long first = inputer.inputSize();
            Long second = inputer.inputSize();
            if (first > second) {
                swap(first, second);
            }
            Predicate<File> predicate = file -> (file.length() >= first && file.length() <= second);
            print(files, predicate);
        } else {
            super.handle(files);
        }
    }

    private void swap(long first, long second) {
        long temp = first;
        first = second;
        second = temp;
    }
}
