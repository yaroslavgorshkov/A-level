package ua.gorshkov.hw20.FactoryMethod;

import ua.gorshkov.hw20.FactoryMethod.LogFactory.LogFactory;
import ua.gorshkov.hw20.FactoryMethod.Logger.Logger;

public class LogManager {
    private final LogFactory logFactory;
    public LogManager(LogFactory logFactory) {
        this.logFactory = logFactory;
    }
    public void processTheLog(LogType type) {
        Logger log = logFactory.createLog(type);
        log.process();
        System.out.println("Log is processed! Bye!");
        System.out.println();
    }
}
