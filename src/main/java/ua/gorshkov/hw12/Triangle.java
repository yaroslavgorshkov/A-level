package ua.gorshkov.hw12;

public class Triangle extends Figure {
    private double firstSideLength;
    private double secondSideLength;
    private double angleValueInRadians;

    public Triangle(double firstSideLength, double secondSideLength, double angleValueInRadians) throws InvalidValueException {
        if (firstSideLength < 0) {
            throw new InvalidValueException("Side length cannot be negative!");
        }
        if (secondSideLength < 0) {
            throw new InvalidValueException("Side length cannot be negative!");
        }
        if (angleValueInRadians < 0 || angleValueInRadians > Math.PI) {
            throw new InvalidValueException("Angle value cannot be negative or bigger than PI!");
        }
        this.firstSideLength = firstSideLength;
        this.secondSideLength = secondSideLength;
        this.angleValueInRadians = angleValueInRadians;
    }

    @Override
    public double perimeter() {
        return firstSideLength + secondSideLength + countThirdSide(firstSideLength, secondSideLength, angleValueInRadians);
    }

    private double countThirdSide(double firstSideLength, double secondSideLength, double angleValueInRadians) {
        return Math.sqrt(Math.pow(firstSideLength, 2) + Math.pow(secondSideLength, 2) -
                2 * firstSideLength * secondSideLength * Math.sin(angleValueInRadians));
    }

    @Override
    public double area() {
        return 0.5 * firstSideLength * secondSideLength * Math.sin(angleValueInRadians);
    }

    public double area(double firstSideLength, double secondSideLength, double thirdSideLength)
            throws InvalidValueException, ImpossibleTriangleException {
        if (firstSideLength + secondSideLength <= thirdSideLength) {
            throw new ImpossibleTriangleException("Impossible triangle!");
        }
        if (thirdSideLength + secondSideLength <= firstSideLength) {
            throw new ImpossibleTriangleException("Impossible triangle!");
        }
        if (firstSideLength + thirdSideLength <= secondSideLength) {
            throw new ImpossibleTriangleException("Impossible triangle!");
        }

        if (firstSideLength < 0) {
            throw new InvalidValueException("Side length cannot be negative!");
        }
        if (secondSideLength < 0) {
            throw new InvalidValueException("Side length cannot be negative!");
        }
        if (thirdSideLength < 0) {
            throw new InvalidValueException("Side length cannot be negative!");
        }
        double halfPerimeter = countHalfPerimeter(firstSideLength, secondSideLength, thirdSideLength);
        return Math.sqrt(halfPerimeter * (halfPerimeter - firstSideLength) * (halfPerimeter - secondSideLength) *
                (halfPerimeter - thirdSideLength));
    }

    private double countHalfPerimeter(double firstSideLength, double secondSideLength, double thirdSideLength) {
        return (firstSideLength + secondSideLength + thirdSideLength) / 2;
    }

    public double area(double base, double height) throws InvalidValueException {
        if (base < 0) {
            throw new InvalidValueException("Side length cannot be negative!");
        }
        if (height < 0) {
            throw new InvalidValueException("Height cannot be negative!");
        }
        return 0.5 * base * height;
    }

    @Override
    public String toString() {
        return "Triangle{area = " + area() + ", perimeter = " + perimeter() + "}";
    }
}
