package src.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {
    private static final Logger logger = LogManager.getLogger();

    public LoggerClass(){}
    public static void info(String message){
        logger.info(message);
    }
}
