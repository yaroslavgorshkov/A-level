package ua.gorshkov.hw5;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[][] initialArray = new int[size][size];
        fillArray(initialArray);
        System.out.println("twoDimArray = ");
        printArray(initialArray);
        int[][] swappedArray = swapColumnsAndRows(initialArray);
        System.out.println();
        System.out.println("swappedArray = ");
        printArray(swappedArray);
    }

    public static void fillArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = ThreadLocalRandom.current().nextInt(1, 21);
            }
        }
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] swapColumnsAndRows(int[][] initialArray) {
        int[][] newArray = new int[initialArray.length][initialArray.length];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                newArray[i][j] = initialArray[j][i];
            }
        }
        return newArray;
    }
}
