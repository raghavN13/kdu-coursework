package org.example.IPL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
enum LogLevel
{
    DEBUG,
    ERROR,
    INFO,
    TRACE
}
public class Log {
    private Log(){}

    public static void customLogger(String message, String type) {
        switch (LogLevel.valueOf(String.valueOf(type).toUpperCase()))  {
            case ERROR -> logger.error(message);
            case DEBUG -> logger.debug(message);
            case TRACE -> logger.trace(message);
            default -> logger.info(message);
        }
    }

    static void customLogger(Double messageDouble, String type) {
        String message = messageDouble.toString();
        switch (LogLevel.valueOf(String.valueOf(type).toUpperCase()))  {
            case ERROR -> logger.error(message);
            case DEBUG -> logger.debug(message);
            case TRACE -> logger.trace(message);
            default -> logger.info(message);
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(Log.class);
}