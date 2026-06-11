package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogger {
    private static final String LOG_DIR = "log";
    private static final String LOG_FILE = "log/app.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            File dir = new File(LOG_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            System.err.println("Không thể tạo thư mục log: " + e.getMessage());
        }
    }

    private static void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] [%-5s] %s", timestamp, level, message);
        
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logEntry);
        } catch (Exception e) {
            System.err.println("Không thể ghi log ra file: " + e.getMessage());
        }
    }

    public static void info(String message) {
        log("INFO", message);
    }

    public static void warn(String message) {
        log("WARN", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    public static void error(String message, Throwable t) {
        log("ERROR", message + " - Exception: " + t.getClass().getName() + ": " + t.getMessage());
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            t.printStackTrace(pw);
        } catch (Exception e) {
            // ignore
        }
    }
}
