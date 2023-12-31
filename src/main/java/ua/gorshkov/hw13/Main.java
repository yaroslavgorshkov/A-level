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

        double difference = 58;
        Optional<Figure> figureLargerByGivenDifferenceInAreaForMinimum = findFigure(figures, difference);

        if (figureLargerByGivenDifferenceInAreaForMinimum.isPresent()) {
            System.out.println("The figure that is larger by a given difference in area for the minimum has = "
                    + figureLargerByGivenDifferenceInAreaForMinimum.get().area() + " area.");
        } else {
            System.out.println("There are not figure in list which area is bigger than minimum figure`s area on " + difference);
        }

        Optional <Figure> figureWithAnAreaTwiceThePerimeter = findFigureWithAnAreaTwiceThePerimeter(figures);
        if (figureWithAnAreaTwiceThePerimeter.isPresent()) {
            System.out.println("The figure with an area twice the perimeter has = "
                    + figureWithAnAreaTwiceThePerimeter.get().area() + " area and "
                    + figureWithAnAreaTwiceThePerimeter.get().perimeter() + " perimeter.");
        } else {
            System.out.println("There are not figure which area is twice the perimeter in list");
        }

    }

    private static Optional<Figure> findFigure(List<Figure> figures, double difference) {
        Comparator<Figure> figureAreaComparator = (o1, o2) -> Double.compare(o1.area(), o2.area());

        Optional<Figure> minAreaFigure = figures.stream().min(figureAreaComparator);

        return figures.stream()
                .filter(element -> element.area() - difference == minAreaFigure.get().area())
                .findFirst();
    }

    private static Optional<Figure> findFigureWithAnAreaTwiceThePerimeter(List<Figure> figures) {
        return figures.stream()
                .filter(element -> element.area()/2==element.perimeter())
                .findFirst();
    }
}
