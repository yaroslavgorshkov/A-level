package ua.gorshkov.hw4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        int[] integerArray = new int[20];
        fillArray(integerArray);
        System.out.println(Arrays.toString(integerArray));
        int result = numberOfPrimeNumbers(integerArray);
        System.out.println("Kоличество простых чисел в integerArray = " + result);
    }

    public static void fillArray(int[] arrayForFilling) {
        for (int i = 0; i < arrayForFilling.length; i++) {
            arrayForFilling[i] = ThreadLocalRandom.current().nextInt(1,21);
        }
    }


    public static int numberOfPrimeNumbers(int[] arrayForCountPrimes) {
        int result = 0;
        for (int i : arrayForCountPrimes) {
            if (isPrime(i)) {
                result++;
            }
        }
        return result;
    }

    public static boolean isPrime(int number) {
        int dividers = 0;
        for (int i = 1; i <= number; i++ )
        {
            if (number%i==0)
            {
                dividers++;
            }
        }
        return dividers == 2;
    }
}
