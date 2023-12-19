package ua.gorshkov.hw12;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Figure circle1;
        Figure circle2;
        Figure triangle1;
        Figure triangle2;
        Figure square1;
        Figure square2;
        try {
            circle1 = new Circle(10);
            circle2 = new Circle(20);
            triangle1 = new Triangle(10, 20, 0.5);
            triangle2 = new Triangle(20, 30, 0.6);
            square1 = new Square(10);
            square2 = new Square(20);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        Comparator<Figure> areaComparator = (o1, o2) -> Double.compare(o1.area(), o2.area());

        Comparator<Figure> perimeterComparator = (o1, o2) -> Double.compare(o1.perimeter(), o2.perimeter());

        Comparator<Figure> linkedComparator = areaComparator.thenComparing(perimeterComparator);

        List<Figure> figureList = new ArrayList<>();
        figureList.add(circle1);
        figureList.add(circle2);
        figureList.add(triangle1);
        figureList.add(triangle2);
        figureList.add(square1);
        figureList.add(square2);

        System.out.println(figureList);
        Collections.sort(figureList, linkedComparator);

        System.out.println(figureList);
    }
}
