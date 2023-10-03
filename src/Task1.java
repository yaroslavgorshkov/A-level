public class Task1 {
    public static void main(String[] args) {
        int pointACoordinateX = 10;
        int pointACoordinateY = 8;

        int pointBCoordinateX = 3;
        int pointBCoordinateY = 4;

        int pointCCoordinateX = 5;
        int pointCCoordinateY = 1;

        System.out.println("Точка А(" + pointACoordinateX + ";" + pointACoordinateY + ")");
        System.out.println("Точка B(" + pointBCoordinateX + ";" + pointBCoordinateY + ")");
        System.out.println("Точка C(" + pointCCoordinateX + ";" + pointCCoordinateY + ")");

        double result = findAreaOfTriangle(pointACoordinateX, pointACoordinateY, pointBCoordinateX, pointBCoordinateY,
                pointCCoordinateX, pointCCoordinateY);
        System.out.println("Площадь треугольника ABC = " + result + " квадратных условных единиц.");
    }

    public static double findAreaOfTriangle (int cordAX, int cordAY, int cordBX, int cordBY, int cordCX, int cordCY)
    {
        double triangleArea = 0.5*((cordBX - cordAX)*(cordCY - cordAY) - (cordCX - cordAX) * (cordBY - cordAY));
        return (triangleArea >= 0 ? triangleArea : -triangleArea);
    }
}
