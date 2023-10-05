package ua.gorshkov.hw2;
public class Task3 {
    public static void main(String[] args) {
        int a = 100;
        int b = -40;
        int c = 50;
        System.out.println("Числа: " + a + ", " + b + ", " + c);

        int result = smallerInModulus(a,b,c);
        System.out.println("Меньшее по модулю число = " + result);
    }

    public static int smallerInModulus(int a, int b, int c)
    {
        int aModulus = (a<=0 ? a : -a);
        int bModulus = (b<=0 ? b : -b);
        int cModulus = (c<=0 ? c : -c);

        if (aModulus >= bModulus)
        {
            return a;
        }
        else if (bModulus >= cModulus)
        {
            return b;
        }
        else
        {
            return c;
        }
    }
}
