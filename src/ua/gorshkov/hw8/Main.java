package ua.gorshkov.hw8;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal("meat", 10);
        Animal cat = new Animal("mouse", 12);
        Animal bee = new Animal("honey", 1);
        String biggestTimeOfSleepingAnimalsFood = getBiggestSleepingTimeAnimalsFood(dog, cat, bee);
        System.out.println("Biggest sleeping time animal`s food = " + biggestTimeOfSleepingAnimalsFood);
    }

    private static String getBiggestSleepingTimeAnimalsFood(Animal first, Animal second, Animal third) {
        int firstHoursOfSleeping = first.getHoursOfSleeping();
        int secondHoursOfSleeping = second.getHoursOfSleeping();
        int thirdHoursOfSleeping = third.getHoursOfSleeping();
        return firstHoursOfSleeping >= secondHoursOfSleeping && firstHoursOfSleeping >= thirdHoursOfSleeping
                ? first.getKindOfFood() : (secondHoursOfSleeping >= thirdHoursOfSleeping
                ? second.getKindOfFood() : third.getKindOfFood());
    }
}
