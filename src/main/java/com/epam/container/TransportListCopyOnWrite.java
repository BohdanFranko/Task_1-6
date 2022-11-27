package com.epam.container;

import java.util.*;

public class TransportListCopyOnWrite<T> implements List<T> {

    private List<T> defaultList;
    private List<T> unmodifiableList;

    public TransportListCopyOnWrite() {
        defaultList = new ArrayList<>();
        unmodifiableList = new ArrayList<>();
    }

    /**
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        snapshot();
        return unmodifiableList.size();
    }

    /**
     * Returns true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        snapshot();
        return unmodifiableList.isEmpty();
    }

    /**
     * @param o - element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        snapshot();
        return unmodifiableList.contains(o);
    }

    /**
     * Class TransportListCopyOnWriteIterator that implements methods of Iterator interface.
     */
    private class TransportListCopyOnWriteIterator implements Iterator<T> {
        private final Object[] array = unmodifiableList.toArray();
        int counter = 0;

        /**
         * Returns true if the iteration has more elements
         *
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return counter < array.length;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public T next() {
            return (T) array[counter++];
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new TransportListCopyOnWriteIterator();
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence (from first to last element)
     */
    @Override
    public Object[] toArray() {
        snapshot();
        return unmodifiableList.toArray();
    }

    /**
     * Returns an array containing all the elements in this list in proper sequence
     *
     * @param t1s  - the array into which the elements of this list are to be stored, if it is big enough
     * @param <T1> - the runtime type of the array to contain the collection
     * @return an array containing the elements of this list
     */
    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        snapshot();
        return unmodifiableList.toArray(t1s);
    }

    /**
     * Appends the specified element to the end of this list
     *
     * @param t - element to be appended to this list
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean add(T t) {
        return defaultList.add(t);
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present
     *
     * @param o - element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        return defaultList.remove(o);
    }

    /**
     * Returns true if this list contains all the elements of the specified collection
     *
     * @param collection - collection to be checked for containment in this list
     * @return true if this list contains all the elements of the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        snapshot();
        return new HashSet<>(unmodifiableList).containsAll(collection);
    }

    /**
     * Appends all the elements in the specified collection to the end of this list
     *
     * @param collection - collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return defaultList.addAll(collection);
    }

    /**
     * @param i          - index at which to insert the first element from the specified collection
     * @param collection - collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return defaultList.addAll(i, collection);
    }

    /**
     * Removes from this list all of its elements that are contained in the specified collection
     *
     * @param collection - collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        return defaultList.removeAll(collection);
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection
     *
     * @param collection - collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        return defaultList.retainAll(collection);
    }

    /**
     * Removes all the elements from this list
     */
    @Override
    public void clear() {
        defaultList.clear();
    }

    /**
     * Returns the element at the specified position in this list
     *
     * @param i - index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public T get(int i) {
        snapshot();
        return unmodifiableList.get(i);
    }

    /**
     * Replaces the element at the specified position in this list with the specified element
     *
     * @param i         - index of the element to replace
     * @param t - element to be stored at the specified position
     */
    @Override
    public T set(int i, T t) {
        return defaultList.set(i, t);
    }

    /**
     * Inserts the specified element at the specified position in this list
     *
     * @param i         - - index at which the specified element is to be inserted
     * @param t -  element to be inserted
     */
    @Override
    public void add(int i, T t) {
        defaultList.add(i, t);
    }

    /**
     * Removes the element at the specified position in this list
     *
     * @param i - the index of the element to be removed
     * @return the element previously at the specified position
     */
    @Override
    public T remove(int i) {
        return defaultList.remove(i);
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list
     *
     * @param o - element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object o) {
        snapshot();
        return unmodifiableList.indexOf(o);
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list
     *
     * @param o - element to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object o) {
        snapshot();
        return unmodifiableList.lastIndexOf(o);
    }

    /**
     * @return a list iterator over the elements in this list (in proper sequence)
     */
    @Override
    public ListIterator<T> listIterator() {
        snapshot();
        return unmodifiableList.listIterator();
    }

    /**
     * @param i - index of the first element to be returned from the list iterator
     * @return a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list
     */
    @Override
    public ListIterator<T> listIterator(int i) {
        snapshot();
        return unmodifiableList.listIterator(i);
    }

    /**
     * @param i - low endpoint (inclusive) of the subList
     * @param i1 - high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     */
    @Override
    public List<T> subList(int i, int i1) {
        snapshot();
        return unmodifiableList.subList(i, i1);
    }

    /**
     * Changes unmodifiableList to contain current defaultList elements.
     */
    private void snapshot() {
        unmodifiableList = Collections.unmodifiableList(defaultList);
    }
}