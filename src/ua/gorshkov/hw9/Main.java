package ua.gorshkov.hw9;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Square square = new Square(10);
        Triangle triangle = new Triangle(8,8, 0.5);

        System.out.println("Circle perimeter = " + circle.perimeter() + ", Circle area = " + circle.area());
        System.out.println("Square perimeter = " + square.perimeter() + ", Square area = " + square.area());
        System.out.println("Triangle perimeter = " + triangle.perimeter() + ", Triangle area = " + triangle.area());

        double areaOfTriangleByHeronsFormula = triangle.area(3,4,5);
        System.out.println("Area of triangle by Herons formula = " + areaOfTriangleByHeronsFormula);
        double areaOfTriangleByABaseHeightProduct = triangle.area(10,15);
        System.out.println("Area of triangle by Herons formula = " + areaOfTriangleByABaseHeightProduct);

    }
}
