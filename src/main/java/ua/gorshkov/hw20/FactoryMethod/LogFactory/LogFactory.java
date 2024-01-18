package ua.gorshkov.hw20.FactoryMethod.LogFactory;

import ua.gorshkov.hw20.FactoryMethod.LogType;
import ua.gorshkov.hw20.FactoryMethod.Logger.Logger;

public interface LogFactory {
    Logger createLog(LogType type);
}
