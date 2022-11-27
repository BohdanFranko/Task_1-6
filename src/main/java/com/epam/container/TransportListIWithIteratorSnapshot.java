package com.epam.container;


import com.epam.transport.Transport;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Class TransportListIWithIteratorSnapshot that extends TransportList class and overrides it's iterator
 * to not change its state while iterating
 */
public class TransportListIWithIteratorSnapshot<T extends Transport> extends TransportList<T> {

    private static class TransportListIterator<T> implements Iterator<T> {

        private final Object[] snapshotContainer = Arrays.copyOf(getContainerOfTransports(), getSize());

        private final int snapshotSize = getSize();
        private Predicate<T> predicate;
        private int counter;

        public TransportListIterator() {
            counter = 0;
        }

        public TransportListIterator(Predicate<T> predicate) {
            this();
            this.predicate = predicate;
        }


        /**
         * Returns true if the iteration has more elements
         *
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            if (counter >= snapshotSize) {
                return false;
            }
            while (predicate != null && !predicate.test((T) snapshotContainer[counter])) {
                if (counter >= snapshotSize) {
                    return false;
                }
                counter++;
            }
            return true;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public T next() {
            return (T) snapshotContainer[counter++];
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new TransportListIterator<>();
    }

    /**
     * Returns an iterator over the elements in specified by Predicate sequence
     *
     * @param predicate - a condition that will be used to determine in what sequence to iterate
     * @return Iterator
     */
    @Override
    public Iterator<T> iterator(Predicate<T> predicate) {
        return new TransportListIterator<>(predicate);
    }
}