package ua.gorshkov.hw11;

import java.rmi.server.RemoteRef;
import java.util.Arrays;

public class ArrayList<T> {
    private Object[] array;
    private int size;
    private int currentIndex;

    ArrayList(int size) {
        this.size = size;
        currentIndex = 0;
        array = new Object[size];
    }

    //Add element to the end of array {1,2,3} -> {1,2,3,4}
    void add(T element) {
        if (currentIndex < size) {
            array[currentIndex++] = element;
        } else {
            Object[] newArray = new Object[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[currentIndex++] = element;
            array = newArray;
            size++;
        }

    }

    //Add element to given position of array {1,2,3} -> {1,4,2,3}
    void add(T element, int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be less than the array size!");
        }
        Object[] newArray = new Object[array.length + 1];
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        newArray[index] = element;
        for (int i = index; i < array.length; i++) {
            newArray[i+1] = array[i];
        }
        currentIndex++;
        array = newArray;
        size++;
    }

    //Returns index of the given element, if element doesnt exist return -1
    int indexOf(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    boolean isContains(T element) {
        for (Object o : array) {
            if (o == element) {
                return true;
            }
        }
        return false;
    }

    T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be less than the array size!");
        }
        return (T) array[index];
    }

    T remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be less than the array size!");
        }
        T removedElement = (T) array[index];

        Object[] newArray = new Object[array.length - 1];
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        for (int i = index+1; i < array.length; i++) {
            newArray[i-1] = array[i];
        }

        array = newArray;
        size--;
        currentIndex--;

        return removedElement;
    }
}

