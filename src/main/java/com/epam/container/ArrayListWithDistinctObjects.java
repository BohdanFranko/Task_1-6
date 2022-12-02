package com.epam.container;

import com.epam.transport.Transport;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class ArrayListWithDistinctObjects that extends class ArrayList that allows to contain only one instance of Class
 *
 * @param <T> - Clas that it will store. Extends class Transport
 */
public class ArrayListWithDistinctObjects<T extends Transport> extends ArrayList<T> {
    @Override
    public T set(int index, T element) {
        if (!containsSameHash(index, element)) {
            return super.set(index, element);
        }
        return null;
    }

    @Override
    public void add(int index, T element) {
        if (containsSameHash(element)) {
            super.add(index, element);
        }
    }

    @Override
    public boolean add(T t) {
        if (containsSameHash(t)) {
            return super.add(t);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int counter = index;
        boolean allAdded = true;
        for (T transport : c) {
            if (containsSameHash(transport)) {
                super.add(counter++, transport);
            } else {
                allAdded = false;
            }
        }
        return allAdded;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean allAdded = true;
        for (T transport : c) {
            if (containsSameHash(transport)) {
                super.add(transport);
            } else {
                allAdded = false;
            }
        }
        return allAdded;
    }

    /**
     * Checks if one container contains an Object that has the same hashCode
     *
     * @param object - an object which will be compared
     * @return true if the list contains specified Object's hashCode
     */
    private boolean containsSameHash(T object) {
        Object[] myArray = toArray();
        for (Object element : myArray) {
            if (object.hashCode() == element.hashCode()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if one container contains an Object that has the same hashCode for set method
     *
     * @param index  - index of element that will be excluded in checking list's elements
     * @param object - an object which will be compared
     * @return true if the list contains specified Object's hashCode
     */
    private boolean containsSameHash(int index, T object) {
        Object[] myArray = toArray();
        for (int i = 0; i < myArray.length; i++) {
            if (i != index && object.hashCode() == myArray[i].hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if one container contains an Object that has the same hashCode
     *
     * @param collection - collection of element that will be compared to list's elements
     * @return true if the list contain at least one collection's element hashCode
     */
    private boolean containsSameHash(Collection<? extends T> collection) {
        Object[] myArray = toArray();
        for (Object element : myArray) {
            for (T collectionElement : collection) {
                if (element.hashCode() == collectionElement.hashCode()) {
                    return true;
                }
            }
        }
        return false;
    }

}
