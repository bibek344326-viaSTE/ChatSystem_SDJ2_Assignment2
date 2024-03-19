package model;

public class Message {
    private String messageBody;
    private String username;

    public Message(String messageBody,String username){
        this.messageBody=messageBody;
        this.username=username;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getUser() {
        return username;
    }

    @Override
    public String toString() {
        return username +": "+messageBody;
    }

    public void setUser(String user) {
        this.username = user;
    }
}
