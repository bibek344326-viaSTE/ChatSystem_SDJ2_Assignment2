package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Log {


    private ArrayList<LogLine> logLines;

    private static Map<String, Log> map = new HashMap<>();

    private Log() {

    }

    public static Log getInstance(String key){
        Log instance = map.get(key);
        if (instance == null){
            synchronized (map){
                instance = map.get(key);
                if (instance == null){
                    instance = new Log();
                    map.put(key, instance);
                }
            }
        }
        return instance;
    }

    public void addLog(String text) {
        LogLine logLine = new LogLine(text);
        this.logLines = new ArrayList<>();
        this.logLines.add(logLine);
        addToFile(logLine);
        System.out.println(logLine);
    }

    public ArrayList<LogLine> getAll() {
        return logLines;
    }

    public String toString() {
        return this.logLines.toString();
    }

    private void addToFile(LogLine log) {
        if (log == null) {
            return;
        }
        BufferedWriter out = null;
        try {
            String filename = "Log-" + log.getDateTime().getSortableDate() + ".txt";
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
