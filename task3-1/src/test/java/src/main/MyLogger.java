package src.main;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    private static MyLogger instance = null;
    private static final Logger logger = LogManager.getLogger();

    private MyLogger() {
    }

    public static synchronized MyLogger getInstance() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    public void debug(String message) {
        logger.debug(message);
    }
    public static void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void fatal(String message) {
        logger.fatal(message);
    }
}
