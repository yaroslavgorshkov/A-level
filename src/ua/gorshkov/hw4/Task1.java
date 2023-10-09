package ua.gorshkov.hw4;
import java.util.concurrent.ThreadLocalRandom;

public class Task1 {
    public static void main(String[] args) {
        int[] integerArray = new int[400];
        fillArray(integerArray);
        double arithmeticalMeanResult = arithmeticalMean(integerArray);
        double geometricalMeanResult = geometricalMean(integerArray);
        System.out.println("Среднее арифметическое integerArray = " + arithmeticalMeanResult);
        System.out.println("Среднее геометрическое integerArray = " + geometricalMeanResult);
    }

    public static void fillArray(int[] arrayForFilling) {
        for (int i = 0; i < arrayForFilling.length; i++) {
            arrayForFilling[i] = ThreadLocalRandom.current().nextInt(1,11);
        }
    }

    public static double arithmeticalMean(int[] arithMeanArr) {
        int sum = 0;
        for(int i : arithMeanArr) {
            sum += i;
        }
        return (double) sum / arithMeanArr.length;
    }

    public static double geometricalMean(int[] geomMeanArr) {
        double product = 1;
        for(int i : geomMeanArr) {
            product *= i;
        }
        return Math.pow(product, (double)1/geomMeanArr.length);
    }
}
