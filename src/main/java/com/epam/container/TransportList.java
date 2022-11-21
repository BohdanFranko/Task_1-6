package com.epam.container;

import com.epam.transport.Transport;

import java.util.*;
import java.util.function.Predicate;

/**
 * Class Container that implements and contains methods of List interface.
 */
public class TransportList implements List<Transport> {
    //Number of fields in Transport class
    private final int NUMBER_OF_FIELDS = 4;
    private final static int INITIAL_CAPACITY = 5;
    private int size;
    Transport[] array;

    //implement easier, remove repeatable rows
    public TransportList() {
        this(INITIAL_CAPACITY);
    }

    public TransportList(int capacity) {
        this.size = 0;
        array = new Transport[capacity];
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
     * Returns an iterator over the elements in this list in specified sequence by ConditionalAuto and conditions[]
     */
    @Override
    public Iterator<Transport> iterator() {
        return new Iterator<Transport>() {
            int counter = 0;
            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public Transport next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[counter++];
            }
        };
    }

    public <T> Iterator<T> iterator(Predicate<T> predicate) {
        return new TransportListIterator<>(this, predicate);
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element)
     */
    @Override
    public Object[] toArray() {
        Transport[] resArray = new Transport[size];
        System.arraycopy(array, 0, resArray, 0, size);
        return resArray;
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element);
     * the runtime type of the returned array is that of the specified array.
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        return (T[]) toArray();
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
        array[size++] = transport;
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
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            array[size] = null;
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
        collection.forEach(obj -> add(i, obj));
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
            if (!collection.contains(array[i])) {
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
        array = new Transport[INITIAL_CAPACITY];
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
            return array[i];
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
        array[i] = transport;
        return array[i];
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
        System.arraycopy(array, i, array, i + 1, size - i);
        array[i] = transport;
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
            Transport resTransport = array[i];
            System.arraycopy(array, i + 1, array, i, size - i - 1);
            size--;
            array[size] = null;
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
            if (array[i].equals(o)) {
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
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Returns an array containing all the elements with new length
     */
    private void resize() {
        if (size == array.length) {
            array = Arrays.copyOf(array, (size * 3) / 2 + 1);
        }
    }

    //NotSupported Exception
    @Override
    public ListIterator<Transport> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Transport> listIterator(int i) {
        return null;
    }

    @Override
    public List<Transport> subList(int i, int i1) {
        return null;
    }


}
