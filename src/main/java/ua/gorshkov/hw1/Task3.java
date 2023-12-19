package ua.gorshkov.hw1;
public class Task3 {
    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("меняем местами а и b...");
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a = " + a + ", b = " + b);
    }
}