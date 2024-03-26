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

    public ChatModelManager(Client client) {
        this.client = client;
        this.support=new PropertyChangeSupport(this);
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
    public void setUsername(User username) {
        this.user = username;
    }


    public List<Message> getMessages() {
        return client.getMessages();
    }






    public List<String> getUsernames() {
        return client.getUserList();
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName,listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {

        support.removePropertyChangeListener(eventName,listener);
    }
}

