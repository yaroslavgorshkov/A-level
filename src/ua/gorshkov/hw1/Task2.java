package ua.gorshkov.hw1;
public class Task2 {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("меняем местами а и b...");
        int buffer = a;
        a = b;
        b = buffer;
        System.out.println("a = " + a + ", b = " + b);
    }
}
