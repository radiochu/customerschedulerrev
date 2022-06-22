package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Logger.
 */
public class Logger {
    /**
     * The constant logFile.
     */
    public static final File logFile = new File("src/login_activity.txt");

    /**
     * Log activity.
     *
     * @param log the log
     * @throws IOException the io exception
     */
    public static void logActivity(String log) throws IOException {

        FileWriter fw = new FileWriter(logFile, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(log);
        pw.close();

    }

}
