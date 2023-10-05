package ua.gorshkov.hw3;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String string = scanner.nextLine();
        int result = wordsInString(string);
        System.out.println("Количество слов в строке = " + result);
    }

    public static int wordsInString(String string)
    {
        if (string.isEmpty())
        {
            return 0;
        }
        int result = 1;
        for(int i = 0; i < string.length()-1; i ++)
        {
            if(string.charAt(i) == ' ' && string.charAt(i+1) != ' ')
            {
                result++;
            }
        }
        return result;
    }
}
