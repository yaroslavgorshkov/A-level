package ua.gorshkov.hw3;
import java.util.Scanner;


public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String string = scanner.nextLine();
        String stringWithoutSpaces = deleteSpacesFromStr(string);
        boolean isPalindrome = isStringPalindrome(stringWithoutSpaces);
        if (isPalindrome)
        {
            System.out.println("Строка - палиндром!");
        }
        else
        {
            System.out.println("Строка - не палиндром!");
        }
    }

    public static String deleteSpacesFromStr(String string)
    {
        String stringWithoutSpaces = "";
        for(int i = 0; i < string.length(); i ++)
        {
            if(string.charAt(i)!=' ')
            {
                stringWithoutSpaces += string.charAt(i);
            }
        }
        return stringWithoutSpaces;
    }

    public static boolean isStringPalindrome(String string)
    {
        String reversedString = "";
        for(int i = string.length()-1; i >= 0; i--)
        {
            reversedString += string.charAt(i);
        }
        return reversedString.equals(string);
    }
}
