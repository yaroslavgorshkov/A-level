package ua.gorshkov.hw20.FactoryMethod.LogFactory;

import ua.gorshkov.hw20.FactoryMethod.*;
import ua.gorshkov.hw20.FactoryMethod.Logger.*;
import ua.gorshkov.hw20.FactoryMethod.Logger.Normal.DBLogger;
import ua.gorshkov.hw20.FactoryMethod.Logger.Normal.EmailLogger;
import ua.gorshkov.hw20.FactoryMethod.Logger.Normal.FileLogger;

public class NormalLogFactory implements LogFactory {
    @Override
    public Logger createLog(LogType type) {
        return switch (type) {
            case FILELOGGER -> new FileLogger();
            case DBLOGGER -> new DBLogger();
            case EMAILLOGGER -> new EmailLogger();
        };
    }
}
