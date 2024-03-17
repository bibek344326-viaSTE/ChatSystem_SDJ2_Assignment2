package model;

import java.io.Serializable;

public class PrivateChat implements Serializable {
    private String username1;
    private String username2;
    private MessageList messageList;
    private Message sendMessage;

    public PrivateChat(String username1, String username2) {
        this.username1 = username1;
        this.username2 = username2;
        messageList = new MessageList();
        this.sendMessage=null;
    }

    public PrivateChat(String username1, String username2, Message sendMessage){
        this.username2=username2;
        this.username1=username1;
        this.sendMessage=sendMessage;
        messageList= new MessageList();
    }

    public Message getSendMessage() {
        return sendMessage;
    }

    public void addMessage(Message message) {
        messageList.addMessage(message);
    }

    public MessageList getMessageList() {
        return messageList;
    }

    public String getUsername2() {
        return username2;
    }

    public String getUsername1() {
        return username1;
    }


    public boolean equals(Object obj) {
        if(obj==null || getClass()!=obj.getClass()){
            return false;
        }
        PrivateChat other  = (PrivateChat) obj;
        return username1.equals(other.username1)&&
                username2.equals(other.username2)&&
                messageList.equals(other.messageList)&&
                sendMessage.equals(other.sendMessage);
    }

    @Override
    public String toString() {
        return "PrivateChat{" +
                "username1='" + username1 + '\'' +
                ", username2='" + username2 + '\'' +
                ", messageList=" + messageList;
    }
}
