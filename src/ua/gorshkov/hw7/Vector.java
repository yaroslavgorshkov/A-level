package ua.gorshkov.hw7;

import java.util.concurrent.ThreadLocalRandom;

public class Vector {
    private int x;
    private int y;
    private int z;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public static Vector vectorProduct(Vector vector1, Vector vector2) {
        return new Vector(vector1.y * vector2.z - vector1.z * vector2.y,
                vector1.z * vector2.x - vector1.x * vector2.z,
                vector1.x * vector2.y - vector1.y * vector2.x);
    }

    public static double cosBetweenVectors(Vector vector1, Vector vector2) {
        return ((vector1.x * vector2.x + vector1.y * vector2.y + vector1.z * vector2.z)
                / (Math.sqrt(Math.pow(vector1.x, 2) + Math.pow(vector1.y, 2) + Math.pow(vector1.z, 2))
                * Math.sqrt(Math.pow(vector2.x, 2) + Math.pow(vector2.y, 2) + Math.pow(vector2.z, 2))));
    }

    public static Vector sum(Vector vector1, Vector vector2) {
        return new Vector(vector1.x + vector2.x,
                vector1.y + vector2.y,
                vector1.z + vector2.z);
    }

    public static Vector dif(Vector vector1, Vector vector2) {
        return new Vector(vector1.x - vector2.x,
                vector1.y - vector2.y,
                vector1.z - vector2.z);
    }

    public static Vector[] getRandomVectorArray(int n) {
        Vector[] vectorArray = new Vector[n];
        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] = new Vector(ThreadLocalRandom.current().nextInt(1, 21),
                    ThreadLocalRandom.current().nextInt(1, 21),
                    ThreadLocalRandom.current().nextInt(1, 21));
        }
        return vectorArray;
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ')';
    }

    public static void main(String[] args) {
        Vector a = new Vector(1, 2, 3);
        Vector b = new Vector(4, 5, 6);
        System.out.println("Vector a" + a);
        System.out.println("Vector b" + b);
        System.out.println();
        System.out.println("Length of a = " + a.length());
        System.out.println("Length of b = " + b.length());
        System.out.println();
        Vector productVector = vectorProduct(a, b);
        System.out.println("Product vector of a and b is " + productVector);
        System.out.println();
        System.out.println("cos between a and b = " + cosBetweenVectors(a, b));
        System.out.println();
        Vector sumVector = sum(a, b);
        Vector difVector = dif(a, b);
        System.out.println("Summa of a and b = " + sumVector);
        System.out.println("Different of a and b = " + difVector);
        System.out.println();
        Vector[] vectorArray = getRandomVectorArray(10);
        System.out.println("Random vector array:");
        for (Vector vector : vectorArray) {
            System.out.println(vector);
        }
    }
}
