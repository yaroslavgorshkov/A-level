package ua.gorshkov.hw13;

public class Square extends Figure {
    private double sideLength;

    public Square(double sideLength){
        if (sideLength < 0) {
            try {
                throw new InvalidValueException("Side length cannot be negative!");
            } catch (InvalidValueException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.exit(1);
            }
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
