package ua.gorshkov.hw5;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        fillArray(array);
        System.out.println("Целый массив: ");
        printArray(array);

        System.out.print("Введите индекс, который хотите удалить: ");
        int index = scanner.nextInt();
        int[] shiftedArray = shiftArrayByIndex(array, index);
        System.out.println("Сдвинутый массив: ");
        printArray(shiftedArray);
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

    public static int[] shiftArrayByIndex(int[] array, int index) {
        if (index >= array.length) {
            return array;
        }
        int[] newArray = new int[array.length-1];
        boolean isAlreadyDeleted = false;
        for (int i = 0; i < newArray.length; i++) {
            if (i == index) {
                isAlreadyDeleted = true;
            }
            if (!isAlreadyDeleted) {
                newArray[i] = array[i];
            } else {
                newArray[i] = array[i+1];
            }
        }
        return newArray;
    }
}
