package ua.gorshkov.hw20.FactoryMethod.Logger.Pirate;

import ua.gorshkov.hw20.FactoryMethod.Logger.Logger;

public class PirateFileLogger implements Logger {
    @Override
    public void process() {
        System.out.println("The pirate invasion to File logger! Ha ha ha!");
    }
}
