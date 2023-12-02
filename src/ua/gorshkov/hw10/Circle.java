package ua.gorshkov.hw10;

public class Circle extends Figure{
    private double radius;
    public Circle(double radius){
        if (radius < 0){
            try {
                throw new InvalidValueException("Radius cannot be negative!");
            } catch (InvalidValueException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.exit(1);
            }
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
}
