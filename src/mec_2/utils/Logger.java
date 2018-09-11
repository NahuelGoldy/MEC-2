package mec_2.utils;

import java.io.FileWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Logger {

    private static Logger logger;
    private ArrayList<String> log;

    private Logger() {
        this.log = new ArrayList<>();
    }

    public static Logger getLoggerInstance() {
        if(logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void log(String message) {
        Instant instant = new Date().toInstant();
        this.log.add(instant.toString() + " - " + message + "\n");
    }

    public void saveLogToFile(String filename) {
        try{
            String timestamp = new Date().toInstant().toString();
            timestamp = (timestamp.replaceAll(":", "-")).split("\\.")[0];

            FileWriter fw = new FileWriter(timestamp + "__" + filename + ".txt");
            for(String line : this.log) {
                fw.write(line + "\r\n");
            }
            fw.close();
        }
        catch (Exception e) {
            System.out.println("ERROR: there was a problem when saving the log file.");
        }
    }

    public void clearLog() {
        this.log = new ArrayList<>();
    }

}
