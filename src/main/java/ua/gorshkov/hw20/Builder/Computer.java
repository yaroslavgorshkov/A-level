package ua.gorshkov.hw20.Builder;

public class Computer {
    private String processor;
    private String graphicsCard;
    private Integer RAM;
    private String discMemory;
    private String monitorResolution;

    public Computer(String processor, String graphicsCard, Integer RAM, String discMemory, String monitorResolution) {
        this.processor = processor;
        this.graphicsCard = graphicsCard;
        this.RAM = RAM;
        this.discMemory = discMemory;
        this.monitorResolution = monitorResolution;
    }
    @Override
    public String toString() {
        return "Computer{" +
                "processor='" + processor + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", RAM=" + RAM +
                ", discMemory=" + discMemory +
                ", monitorResolution='" + monitorResolution + '\'' +
                '}';
    }
}
