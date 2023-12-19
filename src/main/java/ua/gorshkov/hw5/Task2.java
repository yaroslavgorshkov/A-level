package ua.gorshkov.hw5;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        fillArray(array);
        printArray(array);
        System.out.println("Массив array - упорядоченный по убыванию: " + isSortedInDescending(array));
        sortInDescending(array);
        printArray(array);
        System.out.println("Массив array - упорядоченный по убыванию: " + isSortedInDescending(array));
    }

    public static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(1, 21);
        }
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.print(j + "\t");
        }
        System.out.println();
    }

    public static boolean isSortedInDescending(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void sortInDescending(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int buffer = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = buffer;
                }
            }
        }
    }
}
