package ua.gorshkov.hw4;
import java.util.Arrays;

import java.util.concurrent.ThreadLocalRandom;

public class Task3 {
    public static void main(String[] args) {
        int[] integerArray = new int[2000];
        fillArray(integerArray);
        int[] modifiedArray = evenValuesToZeros(integerArray);
    }

    public static void fillArray(int[] arrayForFilling) {
        for (int i = 0; i < arrayForFilling.length; i++) {
            arrayForFilling[i] = ThreadLocalRandom.current().nextInt(1,31);
        }
    }

    public static int[] evenValuesToZeros(int[] sourceArray) {
        int[] targetArray = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < targetArray.length; i ++) {
            if (targetArray[i]%2==0) {
                targetArray[i] = 0;
            }
        }
        return targetArray;
    }
}
