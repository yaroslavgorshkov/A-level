package ua.gorshkov.hw9;

public class Dog extends Animal{
    private String kindOfFood;
    private int hoursOfSleeping;

    public Dog(String kindOfFood, int hoursOfSleeping) {
        super(kindOfFood, hoursOfSleeping);
        this.kindOfFood = kindOfFood;
        this.hoursOfSleeping = hoursOfSleeping;
    }

    public final String getKindOfFood() {
        return kindOfFood;
    }

    public int getHoursOfSleeping() {
        return hoursOfSleeping;
    }

    public void setKindOfFood(String kindOfFood) {
        this.kindOfFood = kindOfFood;
    }

    public void setHoursOfSleeping(int hoursOfSleeping) {
        this.hoursOfSleeping = hoursOfSleeping;
    }

    @Override
    public void eat() {
        super.eat();
    }
}
