package ua.gorshkov.hw9;

public class Triangle extends Figure {
    private double firstSideLength;
    private double secondSideLength;
    private double angleValueInRadians;

    public Triangle(double firstSideLength, double secondSideLength, double angleValueInRadians) {
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

    public double area(double firstSideLength, double secondSideLength, double thirdSideLength) {
        double halfPerimeter = countHalfPerimeter(firstSideLength, secondSideLength, thirdSideLength);
        return Math.sqrt(halfPerimeter * (halfPerimeter - firstSideLength) * (halfPerimeter - secondSideLength) *
                (halfPerimeter - thirdSideLength));
    }

    public double area(double secondSideLength, double height) {
        return 0.5 * secondSideLength * height;
    }

    private double countHalfPerimeter(double firstSideLength, double secondSideLength, double thirdSideLength) {
        return (firstSideLength + secondSideLength + thirdSideLength) / 2;
    }
}
