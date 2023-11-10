package ua.gorshkov.hw11;

public class ArrayList<T> {
    private Object[] array;
    private int size;

    ArrayList(int size) {
        this.size = size;
        array = new Object[size];
    }

    //Add element to the end of array {1,2,3} -> {1,2,3,4}
    void add(T element) {

    }
    //Add element to given position of array {1,2,3} -> {1,4,2,3}
    void add(T element, int index) {

    }
    //Returns index of the given element, if element doesnt exist return -1
    int indexOf(T element) {

    }

    boolean isContains(T element) {

    }
    T get(int index) {
    }
    T remove(int index) {
    }
}

