package ua.gorshkov.hw12;

public class Circle extends Figure{
    private double radius;
    public Circle(double radius) throws InvalidValueException {
        if (radius < 0){
            throw new InvalidValueException("Radius cannot be negative!");
        }
        this.radius = radius;
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public double area() {
        return Math.PI*radius*radius;
    }

    @Override
    public String toString() {
        return "Circle{area = " + area() + ", perimeter = " + perimeter() + "}";
    }
}
