package ua.gorshkov.hw4;

import java.util.concurrent.ThreadLocalRandom;

public class Task2 {
    public static void main(String[] args) {
        int[] integerArray = new int[1000];
        fillArray(integerArray);
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
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }
}
