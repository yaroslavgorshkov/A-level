package ua.gorshkov.hw10;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double circleRadius;
        double squareSideLength;
        double triangleFirstSideLength;
        double triangleSecondSideLength;
        double triangleAngleValueInRadians;
        Circle circle;
        Square square;
        Triangle triangle;
        while(true){
            try {
                System.out.print("Circle radius = ");
                circleRadius = scanner.nextDouble();
                System.out.print("Square side length = ");
                squareSideLength = scanner.nextDouble();
                System.out.print("Triangle first side length = ");
                triangleFirstSideLength = scanner.nextDouble();
                System.out.print("Triangle second side length = ");
                triangleSecondSideLength = scanner.nextDouble();
                System.out.print("Triangle angle value (in radians) = ");
                triangleAngleValueInRadians = scanner.nextDouble();

                circle = new Circle(circleRadius);
                square = new Square(squareSideLength);
                triangle = new Triangle(triangleFirstSideLength,
                        triangleSecondSideLength,
                        triangleAngleValueInRadians);
                break;
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter the values again.");
            }
        }

        System.out.println("Circle perimeter = " + circle.perimeter() + ", Circle area = " + circle.area());
        System.out.println("Square perimeter = " + square.perimeter() + ", Square area = " + square.area());
        System.out.println("Triangle perimeter = " + triangle.perimeter() + ", Triangle area = " + triangle.area());

        System.out.println();
        System.out.println("Calculating area by Heron's formula.");
        double triangleFirstSideLengthInHeronsFormula;
        double triangleSecondSideLengthInHeronsFormula;
        double triangleThirdSideLengthInHeronsFormula;

        double areaOfTriangleByHeronsFormula;
        while(true){
            try {
                System.out.print("Triangle first side length = ");
                triangleFirstSideLengthInHeronsFormula = scanner.nextDouble();
                System.out.print("Triangle second side length = ");
                triangleSecondSideLengthInHeronsFormula = scanner.nextDouble();
                System.out.print("Triangle third side length = ");
                triangleThirdSideLengthInHeronsFormula = scanner.nextDouble();

                areaOfTriangleByHeronsFormula =
                        triangle.area(triangleFirstSideLengthInHeronsFormula,
                                triangleSecondSideLengthInHeronsFormula,
                                triangleThirdSideLengthInHeronsFormula);
                System.out.println("Area of triangle by Herons formula = " + areaOfTriangleByHeronsFormula);
                break;
            } catch (InvalidValueException | ImpossibleTriangleException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter the values again.");
            }
        }

        System.out.println();
        System.out.println("Calculating area by A Height and Base product formula.");
        double triangleBaseInAHeightProductFormula;
        double heightInAHeightProductFormula;

        double areaOfTriangleByABaseHeightProduct;
        while(true){
            try {
                System.out.print("Triangle base side length = ");
                triangleBaseInAHeightProductFormula = scanner.nextDouble();
                System.out.print("Triangle height side length = ");
                heightInAHeightProductFormula = scanner.nextDouble();

                areaOfTriangleByABaseHeightProduct =
                        triangle.area(triangleBaseInAHeightProductFormula,
                                heightInAHeightProductFormula);
                System.out.println("Area of triangle by Height & Base product formula = "
                        + areaOfTriangleByABaseHeightProduct);
                break;
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter the values again.");
            }
        }

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
