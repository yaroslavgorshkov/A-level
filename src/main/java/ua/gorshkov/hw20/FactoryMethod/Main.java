package ua.gorshkov.hw20.FactoryMethod;

import ua.gorshkov.hw20.FactoryMethod.LogFactory.LogFactory;
import ua.gorshkov.hw20.FactoryMethod.LogFactory.NormalLogFactory;
import ua.gorshkov.hw20.FactoryMethod.LogFactory.PirateLogFactory;

public class Main {
    public static void main(String[] args) {
        LogFactory normalLogFactory = new NormalLogFactory();
        LogManager normalLogManager = new LogManager(normalLogFactory);
        normalLogManager.processTheLog(LogType.FILELOGGER);
        normalLogManager.processTheLog(LogType.DBLOGGER);
        normalLogManager.processTheLog(LogType.EMAILLOGGER);

        LogFactory pirateLogFactory = new PirateLogFactory();
        LogManager pirateLogManager = new LogManager(pirateLogFactory);
        pirateLogManager.processTheLog(LogType.FILELOGGER);
        pirateLogManager.processTheLog(LogType.DBLOGGER);
        pirateLogManager.processTheLog(LogType.EMAILLOGGER);
    }
}
