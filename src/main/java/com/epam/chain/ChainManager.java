package com.epam.chain;

import java.io.File;
import java.util.List;

public class ChainManager {
    private List<Handler> chain;

    public ChainManager(List<Handler> list) {
        chain = list;
        setChain();
    }

    private void setChain() {
        Handler handler = chain.get(0);
        for (int i = 1; i < chain.size(); i++) {
            Handler temp = chain.get(i);
            handler.setNext(temp);
            handler = temp;
        }
    }

    public void start(List<File> list) {
        if (chain.size() != 0) {
            chain.get(0).handle(list);
        }
    }
}
