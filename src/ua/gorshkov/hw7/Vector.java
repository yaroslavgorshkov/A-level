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
    //
    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Vector vectorProduct(Vector otherVector) {
        return new Vector(this.y * otherVector.z - this.z * otherVector.y,
                this.z * otherVector.x - this.x * otherVector.z,
                this.x * otherVector.y - this.y * otherVector.x);
    }

    public double cosBetweenVectors(Vector otherVector) {
        return ((this.x * otherVector.x + this.y * otherVector.y + this.z * otherVector.z)
                / (Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2))
                * Math.sqrt(Math.pow(otherVector.x, 2) + Math.pow(otherVector.y, 2) + Math.pow(otherVector.z, 2))));
    }

    public Vector sum(Vector otherVector) {
        return new Vector(this.x + otherVector.x,
                this.y + otherVector.y,
                this.z + otherVector.z);
    }

    public Vector dif(Vector otherVector) {
        return new Vector(this.x - otherVector.x,
                this.y - otherVector.y,
                this.z - otherVector.z);
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
        Vector productVector = a.vectorProduct(b);
        System.out.println("Product vector of a and b is " + productVector);
        System.out.println();
        System.out.println("cos between a and b = " + a.cosBetweenVectors(b));
        System.out.println();
        Vector sumVector = a.sum(b);
        Vector difVector = a.dif(b);
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
