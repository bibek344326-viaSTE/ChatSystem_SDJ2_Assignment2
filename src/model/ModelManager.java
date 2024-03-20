package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModelManager implements Model {
    private List<Message> messageList;
    private List<PrivateChat> privateMessageList;
    private PropertyChangeSupport property;

    private UserList everyUsers;


    public ModelManager() {
        privateMessageList = new ArrayList<>();
        messageList = new ArrayList<>();
        property = new PropertyChangeSupport(this);
        everyUsers= new UserList();
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




    @Override
    public boolean addUser(User user) {
        return false;
    }

    public void removeUser(User user) {
        everyUsers.removeUser(user);
    }

    public boolean isConnectionPossible(String userName) {
        return !(everyUsers.allUserNames().contains(userName));
    }

    public boolean isLoginPossible(User user) {
        return everyUsers.contains(user);
    }

    public List<String> getAllUsers() {
        return everyUsers.allUserNames();
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
