package ua.gorshkov.hw10;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(12);
        Square square = new Square(8);
        Triangle triangle = new Triangle(3, 4, 3.14);

        System.out.println("Circle perimeter = " + circle.perimeter() + ", Circle area = " + circle.area());
        System.out.println("Square perimeter = " + square.perimeter() + ", Square area = " + square.area());
        System.out.println("Triangle perimeter = " + triangle.perimeter() + ", Triangle area = " + triangle.area());

        System.out.println();
        System.out.println("Calculating area by Heron's formula. First side length = " + 3 + ", second = " + 4 + ", third = " + 5);
        double areaOfTriangleByHeronsFormula = triangle.area(3, 4, 5);
        System.out.println("Area of triangle by Herons formula = " + areaOfTriangleByHeronsFormula);

        System.out.println();
        System.out.println("Calculating area by A Height and Base product formula. Base = " + 10 + ", height = " + 8);
        double areaOfTriangleByABaseHeightProduct = triangle.area(10, 8);
        System.out.println("Area of triangle by Height & Base product formula = " + areaOfTriangleByABaseHeightProduct);

        System.out.println();
        Figure[] figures = new Figure[3];
        figures[0] = circle;
        figures[1] = square;
        figures[2] = triangle;

        Figure biggestAreaFigure = findFigureWithTheBiggestArea(figures);
        System.out.println("Biggest area figure has " + biggestAreaFigure.area() + " area.");
        System.out.println("Biggest area figure is object of " + biggestAreaFigure.getClass() + ".");
    }


    private static Figure findFigureWithTheBiggestArea(Figure[] figures) {
        Figure biggestAreaFigure = figures[0];
        for (Figure figure : figures) {
            if (figure.area() >= biggestAreaFigure.area()) {
                biggestAreaFigure = figure;
            }
        }
        return biggestAreaFigure;
    }
}
