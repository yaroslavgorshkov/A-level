package ua.gorshkov.hw9;

public class Circle extends Figure{
    private double radius;
    public final double pi = 3.1415;
    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double perimeter() {
        return 2*pi*radius;
    }

    @Override
    public double area() {
        return pi*radius*radius;
    }
}
