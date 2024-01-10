package ua.gorshkov.hw20.Builder;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.awt.*;

public class ComputerBuilder {
    private String processor;
    private String graphicsCard;
    private Integer RAM;
    private String discMemory;
    private String monitorResolution;

    public ComputerBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }
    public ComputerBuilder setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }
    public ComputerBuilder setRAM(Integer RAM) {
        this.RAM = RAM;
        return this;
    }
    public ComputerBuilder setDiscMemory(String discMemory) {
        this.discMemory = discMemory;
        return this;
    }
    public ComputerBuilder setMonitorResolution(String monitorResolution) {
        this.monitorResolution = monitorResolution;
        return this;
    }

    public Computer biuld() {
        Computer computer = new Computer(processor, graphicsCard, RAM, discMemory, monitorResolution);
        reset();
        return computer;
    }

    private void reset() {
        this.processor = null;
        this.graphicsCard = null;
        this.RAM = null;
        this.discMemory = null;
        this.monitorResolution = null;
    }
}
