package com.epam.chain;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public interface Handler {
    void setNext(Handler next);

    void handle(List<File> files);

    void print(List<File> files, Predicate<File> predicate);
}
