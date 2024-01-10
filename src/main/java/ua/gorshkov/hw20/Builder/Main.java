package ua.gorshkov.hw20.Builder;

public class Main {
    public static void main(String[] args) {
        ComputerBuilder computerBuilder = new ComputerBuilder();
        Computer computer1 = computerBuilder
                .setProcessor("i9")
                .setGraphicsCard("4090")
                .setRAM(64)
                .setDiscMemory("2T")
                .setMonitorResolution("1920/1080")
                .biuld();

        Computer computer2 = computerBuilder
                .setGraphicsCard("4090")
                .setDiscMemory("2T")
                .setMonitorResolution("1920/1080")
                .biuld();

        System.out.println("comp1 = " + computer1);
        System.out.println("comp2 = " + computer2);
    }
}
