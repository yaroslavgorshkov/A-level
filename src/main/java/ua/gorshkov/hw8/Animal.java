package ua.gorshkov.hw8;

public class Animal {
    private String kindOfFood;
    private int hoursOfSleeping;

    public String getKindOfFood() {
        return this.kindOfFood;
    }

    public int getHoursOfSleeping() {
        return this.hoursOfSleeping;
    }

    public Animal(String kindOfFood, int hoursOfSleeping) {
        this.kindOfFood = kindOfFood;
        this.hoursOfSleeping = hoursOfSleeping;
    }

    public Animal() {
    }

    public void eat() {
        System.out.println("Eat from animal " + this.kindOfFood);
    }

    public final void sleep() {
        System.out.println("Sleep from animal " + this.hoursOfSleeping);
    }
}
