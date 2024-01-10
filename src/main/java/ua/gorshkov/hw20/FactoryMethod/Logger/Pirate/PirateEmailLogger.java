package ua.gorshkov.hw20.FactoryMethod.Logger.Pirate;

import ua.gorshkov.hw20.FactoryMethod.Logger.Logger;

public class PirateEmailLogger implements Logger {
    @Override
    public void process() {
        System.out.println("The pirate invasion to Email logger! Ha ha ha!");
    }
}
