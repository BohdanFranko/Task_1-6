package com.epam.main;

import com.epam.chain.*;

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
        ChainManager chainManager = new ChainManager(List.of(new FileByNameHandler(), new FileByExtensionHandler(), new FileBySizeHandler(),
                new FileByDateOfChangeHandler()));
        chainManager.start(lst);

    }
}
