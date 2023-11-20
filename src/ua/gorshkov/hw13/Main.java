package ua.gorshkov.hw13;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Circle circle1 = new Circle(10);
        System.out.println("First circle area = " + circle1.area());
        Circle circle2 = new Circle(12);
        System.out.println("Second circle area = " + circle2.area());

        Square square1 = new Square(11);
        System.out.println("First square area = " + square1.area());
        Square square2 = new Square(8);
        System.out.println("Second square area = " + square2.area());

        Triangle triangle1 = new Triangle(40, 50, 0.3);
        System.out.println("First triangle area = " + triangle1.area());
        Triangle triangle2 = new Triangle(40, 60, 0.6);
        System.out.println("Second triangle area = " + triangle2.area());

        List<Figure> figures = new ArrayList<>(Arrays.asList(circle1, circle2, square1, square2, triangle1, triangle2));

        Figure figureLargerByGivenDifferenceInAreaForMinimum = findFigure(figures, 57);

        System.out.println("The figure that is larger by a given difference in area for the minimum has = "
                + figureLargerByGivenDifferenceInAreaForMinimum.area() + " area.");

        Figure figureWithAnAreaTwiceThePerimeter = findFigureWithAnAreaTwiceThePerimeter(figures);
        System.out.println("The figure with an area twice the perimeter has = "
                + figureWithAnAreaTwiceThePerimeter.area() + " area and "
                + figureWithAnAreaTwiceThePerimeter.perimeter() + " perimeter.");
    }

    private static Figure findFigure(List<Figure> figures, double difference) {
        Comparator<Figure> figureAreaComparator = (o1, o2) -> Double.compare(o1.area(), o2.area());

        Optional<Figure> minAreaFigure = figures.stream().min(figureAreaComparator);

        Optional<Figure> differenceElement = figures.stream()
                .filter(element -> element.area() - difference == minAreaFigure.get().area())
                .findFirst();

        if (differenceElement.isPresent()) {
            return differenceElement.get();
        } else {
            System.out.println("Error!");
            System.exit(1);
            return null;
        }
    }

    private static Figure findFigureWithAnAreaTwiceThePerimeter(List<Figure> figures) {
        Optional<Figure> result = figures.stream()
                .filter(element -> element.area()/2==element.perimeter())
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            System.out.println("Error!");
            System.exit(1);
            return null;
        }
    }
}
