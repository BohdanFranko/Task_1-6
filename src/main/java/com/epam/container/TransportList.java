package com.epam.container;

import com.epam.exceptions.NotSupportedException;
import com.epam.transport.Transport;

import java.util.*;
import java.util.function.Predicate;

/**
 * Class TransportList that implements and contains methods of List interface.
 */
public class TransportList implements List<Transport> {

    private final int NUMBER_OF_FIELDS = 4;
    private final static int INITIAL_CAPACITY = 5;
    private static int size;
    private static Transport[] containerOfTransports;

    public TransportList() {
        this(INITIAL_CAPACITY);
    }

    public TransportList(int capacity) {
        size = 0;
        containerOfTransports = new Transport[capacity];
    }

    /**
     * Class TransportListIterator that implements methods of Iterator interface.
     */
    private static class TransportListIterator<T> implements Iterator<T> {

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
            if (counter >= size) {
                return false;
            }
            while (predicate != null && !predicate.test((T) containerOfTransports[counter])) {
                if (counter >= size) {
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
            return (T) containerOfTransports[counter++];
        }
    }

    /**
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param o - element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<Transport> iterator() {
        return new TransportListIterator<Transport>();
    }

    /**
     * Returns an iterator over the elements in specified by Predicate sequence
     *
     * @param predicate - a condition that will be used to determine in what sequence to iterate
     * @param <T>       - A type
     * @return Iterator
     */
    public <T> Iterator<T> iterator(Predicate<T> predicate) {
        return new TransportListIterator<>(predicate);
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element)
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(containerOfTransports, size);
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element);
     * the runtime type of the returned array is that of the specified array.
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        return Arrays.copyOf(ts, size);
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param transport - element to be appended to this list
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean add(Transport transport) {
        resize();
        containerOfTransports[size++] = transport;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present
     *
     * @param o - element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0 && index < size) {
            System.arraycopy(containerOfTransports, index + 1, containerOfTransports, index, size - index - 1);
            size--;
            containerOfTransports[size] = null;
            return true;
        }
        return false;
    }

    /**
     * Returns true if this list contains all the elements of the specified collection
     *
     * @param collection - collection to be checked for containment in this list
     * @return true if this list contains all the elements of the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o)) return false;
        }
        return true;
    }

    /**
     * Appends all the elements in the specified collection to the end of this list
     *
     * @param collection - collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends Transport> collection) {
        collection.forEach(this::add);
        return true;
    }

    /**
     * @param i          - index at which to insert the first element from the specified collection
     * @param collection - collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(int i, Collection<? extends Transport> collection) {
        for (Transport transport : collection) {
            add(i++,transport);
        }
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the specified collection
     *
     * @param collection - collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!remove(o)) return false;
        }
        return true;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection
     *
     * @param collection - collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean changed = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!collection.contains(containerOfTransports[i])) {
                remove(i);
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Removes all the elements from this list
     */
    @Override
    public void clear() {
        containerOfTransports = new Transport[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list
     *
     * @param i - index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public Transport get(int i) {
        if (i >= 0 && i < size) {
            return containerOfTransports[i];
        } else throw new IndexOutOfBoundsException("Index must be within array's bounds");
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     *
     * @param i         - index of the element to replace
     * @param transport - element to be stored at the specified position
     */
    @Override
    public Transport set(int i, Transport transport) {
        Objects.checkIndex(i, size);
        containerOfTransports[i] = transport;
        return containerOfTransports[i];
    }

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param i         - - index at which the specified element is to be inserted
     * @param transport -  element to be inserted
     */
    @Override
    public void add(int i, Transport transport) {
        resize();
        System.arraycopy(containerOfTransports, i, containerOfTransports, i + 1, size - i);
        containerOfTransports[i] = transport;
        size++;
    }

    /**
     * Removes the element at the specified position in this list
     *
     * @param i - the index of the element to be removed
     * @return the element previously at the specified position
     */
    @Override
    public Transport remove(int i) {
        if (i >= 0 && i < size) {
            Transport resTransport = containerOfTransports[i];
            System.arraycopy(containerOfTransports, i + 1, containerOfTransports, i, size - i - 1);
            size--;
            containerOfTransports[size] = null;
            return resTransport;
        }
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list
     *
     * @param o - element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (containerOfTransports[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list
     *
     * @param o - element to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (containerOfTransports[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns an array containing all the elements with new length
     */
    private void resize() {
        if (size == containerOfTransports.length) {
            containerOfTransports = Arrays.copyOf(containerOfTransports, (size * 3) / 2 + 1);
        }
    }

    /**
     * Throws NotSupportedException
     *
     * @return
     */
    @Override
    public ListIterator<Transport> listIterator() {
        try {
            throw new NotSupportedException();
        } catch (NotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Throws NotSupportedException
     *
     * @return
     */
    @Override
    public ListIterator<Transport> listIterator(int i) {
        try {
            throw new NotSupportedException();
        } catch (NotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Throws NotSupportedException
     *
     * @return
     */
    @Override
    public List<Transport> subList(int i, int i1) {
        try {
            throw new NotSupportedException();
        } catch (NotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


}
