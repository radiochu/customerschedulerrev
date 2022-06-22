package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    public static final File logFile = new File("src/login_activity.txt");

    public static void logActivity(String log) throws IOException {

        FileWriter fw = new FileWriter(logFile, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(log);
        pw.close();

    }

}
