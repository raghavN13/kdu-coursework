package org.example;
import org.slf4j.LoggerFactory;
public class Logging {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logging.class);
    // Private constructor to hide the implicit public one
    private Logging() {
        throw new AssertionError("Instantiation not allowed for logger class.");
    }
    public static void logInfo(String s) {
        logger.info(s);
    }
    public static void logWarn(String s) {
        logger.warn(s);
    }
    public static void logErr(String s) {
        logger.error(s);
    }
}