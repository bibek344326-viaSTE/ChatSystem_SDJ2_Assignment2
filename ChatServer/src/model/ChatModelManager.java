package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ChatModelManager {
    private List<Message> messageList;
    private List<PrivateChat> privateMessageList;
    private PropertyChangeSupport support;


    public ChatModelManager() {
        privateMessageList = new ArrayList<>();
        messageList = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }
    public void addMessage(Message message) {
        messageList.add(message);
        //  support.firePropertyChange("addMessage",null,message);   // message OR messageList to send ??
    }

    public void addPrivateMessage(PrivateChat privateMessage) {
        if (doesPrivateMessageExists(privateMessage)){
            for (int i = 0; i < privateMessageList.size(); i++) {
                if (privateMessage.equals(privateMessageList.get(i))){
                    privateMessageList.get(i).addMessage(privateMessage.getSendMessage());
                    System.out.println("Message added to existing");
                }

            }
        }
        else{
            privateMessage.addMessage(privateMessage.getSendMessage());
            privateMessageList.add(privateMessage);
            System.out.println("Message added to new");
        }


    }

    public List<Message> getPrivateMessage(PrivateChat arg) {
        for (int i = 0; i < privateMessageList.size(); i++) {
            if (arg.equals(privateMessageList.get(i))){
                return privateMessageList.get(i).getMessageList().getMessages();
            }
        }
        return null;
    }


    private boolean doesPrivateMessageExists(PrivateChat privateMessage) {
        for (int i = 0; i < privateMessageList.size(); i++) {
            if (privateMessageList.get(i).equals(privateMessage)){
                return true;
            }

        }
        return false;
    }

    public List<Message> getMessages() {
        return messageList;
    }


    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
