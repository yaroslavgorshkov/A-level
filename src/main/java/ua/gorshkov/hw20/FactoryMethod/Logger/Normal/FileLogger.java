package ua.gorshkov.hw20.FactoryMethod.Logger.Normal;

import ua.gorshkov.hw20.FactoryMethod.Logger.Logger;

public class FileLogger implements Logger {
    @Override
    public void process() {
        System.out.println("Log data from File logger!");
    }
}
