package ua.gorshkov.hw20.FactoryMethod.LogFactory;

import ua.gorshkov.hw20.FactoryMethod.*;
import ua.gorshkov.hw20.FactoryMethod.Logger.*;
import ua.gorshkov.hw20.FactoryMethod.Logger.Pirate.PirateDBLogger;
import ua.gorshkov.hw20.FactoryMethod.Logger.Pirate.PirateEmailLogger;
import ua.gorshkov.hw20.FactoryMethod.Logger.Pirate.PirateFileLogger;

public class PirateLogFactory implements LogFactory {
    public Logger createLog(LogType type) {
        return switch (type) {
            case FILELOGGER -> new PirateFileLogger();
            case DBLOGGER -> new PirateDBLogger();
            case EMAILLOGGER -> new PirateEmailLogger();
        };
    }
}
