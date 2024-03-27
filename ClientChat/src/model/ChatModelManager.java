package model;

import mediator.Client;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;



public class ChatModelManager implements ChatModel, NamedPropertyChangeSubject {
    private Client client;
    private User user;
    private PropertyChangeSupport support;

    public ChatModelManager(Client client, User user) {
        this.client = client;
        this.user = user;
        this.support = new PropertyChangeSupport(this);
        client.addListener("addMessage",this::messageAdded);
        client.addListener("userNameAdded",this::userNameAdded);
    }
    private void messageAdded(PropertyChangeEvent event) {
        Message message =(Message) event.getNewValue();
        support.firePropertyChange("addMessage",null,message);
    }
    private void userNameAdded(PropertyChangeEvent event){
        String username = (String) event.getNewValue();
        support.firePropertyChange("userNameAdded",null,username);
    }
    @Override
    public void sendMessage(Message message) {
        client.sendMessage(message);
    }


    @Override
    public List<Message> getMessages() {
        return client.getMessages();
    }





    @Override
    public List<String> getUsernames() {
        return client.getUserList();
    }

    @Override
    public User getUsername() {
        return user;
    }

    @Override
    public void setUsername(User username) {
        this.user = username;
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {

    }


//    @Override
//    public void sendPrivateMessage(PrivateChat privateMessage) {
//        client.sendPrivateMessage(privateMessage);
//    }
//
//    @Override
//    public boolean doesPrivateMessageExists(String username1, String username2) {
//        return client.doesPrivateMessageExists(username1, username2);
//    }
//
//    @Override
//    public List<Message> getUsersMessage(String userName1, String userName2) {
//        PrivateChat privateMessage = new PrivateChat(userName1, userName2);
//        return client.getUsersMessage(privateMessage);
//    }
}

