package ua.gorshkov.hw12;

public class Square extends Figure{
    private double sideLength;

    public Square(double sideLength) throws InvalidValueException {
        if (sideLength < 0) {
            throw new InvalidValueException("Side length cannot be negative!");
        }
        this.sideLength = sideLength;
    }

    @Override
    public double perimeter() {
        return sideLength*4;
    }

    @Override
    public double area() {
        return sideLength*sideLength;
    }

    @Override
    public String toString() {
        return "Square{area = " + area() + ", perimeter = " + perimeter() + "}";
    }
}
