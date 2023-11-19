package ua.gorshkov.hw8;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Animal("meat", 10);
        Animal cat = new Animal("mouse", 12);
        Animal bee = new Animal("honey", 1);

        Animal[] amimals = new Animal[3];
        amimals[0] = dog;
        amimals[1] = cat;
        amimals[2] = bee;

        String biggestTimeOfSleepingAnimalsFood = getBiggestSleepingTimeAnimalsFood(amimals);
        System.out.println("Biggest sleeping time animal`s food = " + biggestTimeOfSleepingAnimalsFood);
    }

    private static String getBiggestSleepingTimeAnimalsFood(Animal[] amimals) {
        Animal biggestSleepingTimeAnimal = amimals[0];
        for(int i = 1; i < amimals.length; i ++) {
            if(amimals[i].getHoursOfSleeping()>biggestSleepingTimeAnimal.getHoursOfSleeping()){
                biggestSleepingTimeAnimal = amimals[i];
            }
        }
        return biggestSleepingTimeAnimal.getKindOfFood();
    }
}
