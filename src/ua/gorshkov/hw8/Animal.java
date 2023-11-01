package ua.gorshkov.hw8;

public class Animal {
    private String kindOfFood;
    private int hoursOfSleeping;

    public Animal(String kindOfFood, int hoursOfSleeping) {
        this.kindOfFood = kindOfFood;
        this.hoursOfSleeping = hoursOfSleeping;
    }

    public Animal() {
    }

    public void eat(){
        System.out.println("Eat from animal " + kindOfFood);
    }

    public final void sleep(){
        System.out.println("Sleep from animal " + hoursOfSleeping);
    }

    public static int getBiggestSleepingTime(Animal first, Animal second, Animal third) {
        return (first.hoursOfSleeping>=second.hoursOfSleeping
                && first.hoursOfSleeping>=third.hoursOfSleeping )
                ? first.hoursOfSleeping : Math.max(second.hoursOfSleeping, third.hoursOfSleeping);
    }
}
