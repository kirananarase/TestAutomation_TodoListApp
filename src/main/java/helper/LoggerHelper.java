package helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class LoggerHelper {
    private static boolean root=false;

    public static Logger getLogger(Class cls){
        if(root){
            return Logger.getLogger(cls);
        }
        PropertyConfigurator.configure(new File("")
                .getAbsolutePath() +"/config/log4j.properties");
        root = true;
        return Logger.getLogger(cls);
    }

    public static void main(String[] args) {
        Logger log = LoggerHelper.getLogger(LoggerHelper.class);
    }
}
