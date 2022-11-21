package com.epam.container;

import java.util.Iterator;
import java.util.function.Predicate;

public class TransportListIterator<T> implements Iterator<T> {

    private Predicate<T> predicate;
    private final TransportList transportLists;
    private int counter;

    public TransportListIterator(TransportList array) {
        counter = 0;
        transportLists = array;
    }

    public TransportListIterator(TransportList array, Predicate<T> predicate) {
        this(array);
        this.predicate = predicate;
    }


    /**
     * @return
     */
    @Override
    public boolean hasNext() {
        if (counter >= transportLists.size()) {
            return false;
        }
        while (!predicate.test((T) transportLists.get(counter))) {
            if (counter >= transportLists.size()) {
                return false;
            }
            counter++;
        }
        return true;
    }

    /**
     * @return
     */
    @Override
    public T next() {
        return (T) transportLists.get(counter++);
    }
}
