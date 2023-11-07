package ua.gorshkov.hw9;

public class Square extends Figure{
    private double sideLength;

    public Square(double sideLength) {
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
}
