package ua.gorshkov.hw8;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal("meat", 10);
        Animal cat = new Animal("mouse", 12);
        Animal bee = new Animal("honey", 1);
        int biggestTimeOfSleeping = getBiggestSleepingTime(dog, cat, bee);
        System.out.println("Biggest sleeping time = " + biggestTimeOfSleeping);
    }
    private static int getBiggestSleepingTime(Animal first, Animal second, Animal third) {
        int firstHoursOfSleeping = first.getHoursOfSleeping();
        int secondHoursOfSleeping = second.getHoursOfSleeping();
        int thirdHoursOfSleeping = third.getHoursOfSleeping();

        return (firstHoursOfSleeping>=secondHoursOfSleeping
                && firstHoursOfSleeping>=thirdHoursOfSleeping )
                ? firstHoursOfSleeping : Math.max(secondHoursOfSleeping, thirdHoursOfSleeping);
    }

}
