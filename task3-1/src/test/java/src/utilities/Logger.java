package src.utilities;

public class Logger {

    private Logger logger;

    private  Logger(){
    }

    public void info (String message){
        System.out.println(message);
    }
    public void debug(String message){
        System.out.println(message);
    }

}
