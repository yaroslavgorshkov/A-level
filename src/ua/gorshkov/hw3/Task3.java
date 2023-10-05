package ua.gorshkov.hw3;
import java.util.Random;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Random random = new Random();
        int computerNumber = random.nextInt(11);
        System.out.println("Компьютер загадал число от 0 до 10! Попробуйте его отгадать!");
        guessTheNumber(computerNumber);
    }

    public static void guessTheNumber(int computerNumber)
    {
        System.out.println("Введите число: ");
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            int myNumber = scanner.nextInt();
            if (myNumber > computerNumber)
            {
                System.out.println("Много! Попробуй еще раз!");
            }
            else if(myNumber < computerNumber)
            {
                System.out.println("Мало! Попробуй еще раз!");
            }
            else
            {
                System.out.println("Угадал!");
                break;
            }
        }
    }
}
