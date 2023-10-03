public class Task2 {
    public static void main(String[] args) {
        int number = 122212123;
        System.out.println("number = " + number);
        boolean result = isEven(number);
        if (result)
        {
            System.out.println("number - четное число");
        }
        else
        {
            System.out.println("number - нечетное число");
        }
    }

    public static boolean isEven (int number)
    {
        return number%2==0;
    }
}
