package com.epam.chain;

import com.epam.date.DateInputer;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Predicate;

public class FileByDateOfChangeHandler extends BaseHandler {
    @Override
    public void handle(List<File> files) {
        System.out.println("Find files by lastModified 1/0");
        Inputer inputer = new Inputer();
        if (inputer.input()) {
            DateInputer dateInputer = new DateInputer();
            long date1Time = dateInputer.date().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long date2Time = dateInputer.date().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Predicate<File> predicate = file -> (file.lastModified() >= date1Time && file.lastModified() <= date2Time);
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
