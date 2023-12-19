package ua.gorshkov.module2;

public class Product {
    private String name;
    private int capacity;
    private double costOfOne;

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getCostOfOne() {
        return costOfOne;
    }

    public Product(String name, int capacity, double costOfOne) {
        this.name = name;
        this.capacity = capacity;
        this.costOfOne = costOfOne;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", costOfOne=" + costOfOne +
                '}';
    }
}
