package ua.gorshkov.hw20.FactoryMethod.Logger.Pirate;

import ua.gorshkov.hw20.FactoryMethod.Logger.Logger;

public class PirateDBLogger implements Logger {
    @Override
    public void process() {
        System.out.println("The pirate invasion to Db logger! Ha ha ha!");
    }
}
