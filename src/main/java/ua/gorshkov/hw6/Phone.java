package ua.gorshkov.hw6;

public class Phone {
    String number;
    String model;
    int weight;

    public Phone(String number, String model, int weight) {
        this.number = number;
        this.model = model;
        this.weight = weight;
    }

    public Phone(String number, String model) {
        this.number = number;
        this.model = model;
    }

    public Phone() {
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", weight=" + weight +
                '}';
    }

    public void receiveCall(String name) {
        System.out.println("Звонит {" + name + "}.");
    }

    public String getNumber() {
        return number;
    }

    public static void main(String[] args) {
        Phone firstPhone = new Phone("0980980908", "Xiaomi", 200);
        Phone secondPhone = new Phone("0990990909", "Samsung");
        secondPhone.weight = 250;
        Phone thirdPhone = new Phone();
        thirdPhone.model = "iPhone";
        thirdPhone.number = "0970970907";
        thirdPhone.weight = 300;

        System.out.println(firstPhone);
        System.out.println(secondPhone);
        System.out.println(thirdPhone);

        firstPhone.receiveCall("Alex");
        secondPhone.receiveCall("Yarik");
        thirdPhone.receiveCall("Nastya");

        System.out.println("firstPhone number = " + firstPhone.getNumber());
        System.out.println("secondPhone number = " + secondPhone.getNumber());
        System.out.println("thirdPhone number = " + thirdPhone.getNumber());
    }
}
