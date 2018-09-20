package mec_2.utils;

import mec_2.model.Node;

import java.io.FileWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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

    public void log(String message, Set<Node> set) {
        Instant instant = new Date().toInstant();
        StringBuilder unsettled = new StringBuilder("[");
        for(Node each : set) {
            unsettled.append(each.getName());
            unsettled.append(", ");
        }
        if(!unsettled.toString().equals("[")) unsettled = new StringBuilder(unsettled.substring(0, unsettled.length() - 2));
        unsettled.append("]");
        this.log.add(instant.toString() + " - " + message + unsettled + "\n");
    }

    public void log(String message, Map<Node, Integer> adjacents) {
        Instant instant = new Date().toInstant();
        StringBuilder adjList = new StringBuilder("[");
        for(Node each : adjacents.keySet()) {
            adjList.append(each.getName());
            adjList.append("(");
            adjList.append(adjacents.get(each));
            adjList.append("), ");
        }
        if(!adjList.toString().equals("[")) adjList = new StringBuilder(adjList.substring(0, adjList.length() - 2));
        adjList.append("]");
        this.log.add(instant.toString() + " - " + message + adjList + "\n");
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
