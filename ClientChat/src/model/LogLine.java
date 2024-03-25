package model;

public class LogLine {

    private String text;

    private DateTime dateTime;

    public LogLine(String text){
        this.text = text;
        this.dateTime = new DateTime();
    }

    public String getText(){
        return text;
    }

    public DateTime getDateTime(){
        return dateTime;
    }

    public String toString(){
        return this.dateTime + " " + this.text;
    }
}
