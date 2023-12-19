package ua.gorshkov.hw5;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        System.out.print("Введите количество строк в массиве: ");
        int row = scanner.nextInt();

        System.out.print("Введите количество колонок в массиве: ");
        int col = scanner.nextInt();

        int[][] twoDimArray = new int[row][col];
        System.out.println("До: ");
        printArray(twoDimArray);
        fillArrayWithIndexes(twoDimArray);
        System.out.println();
        System.out.println("После: ");
        printArray(twoDimArray);

    }

    public static void fillArrayWithIndexes(int[][] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(i%2==0) {
                    array[i][j] = ++index;
                    continue;
                }
                array[i][j] = -(++index);
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
}
