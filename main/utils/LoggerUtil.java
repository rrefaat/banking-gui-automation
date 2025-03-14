package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {
    public static void log(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("[LOG " + timestamp + "] " + message);
    }

    public static void logError(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.err.println("[ERROR " + timestamp + "] " + message);
    }
}
