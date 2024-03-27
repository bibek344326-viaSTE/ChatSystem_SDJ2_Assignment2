package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatModel;

import model.Log;
import model.Message;
import model.ModelFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class ChatViewModel implements PropertyChangeListener {
    private StringProperty headerProperty, inputProperty, errorProperty, listProperty;
    private ChatModel model;
    private ViewModelState viewModelState;

    private ObservableList<String> userList;
    private ObservableList<Message> messages;
    private ModelFactory modelFactory;


    public ChatViewModel(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        //headerProperty = new SimpleStringProperty();
        inputProperty = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
        listProperty = new SimpleStringProperty();


        model.addListener("Message", this);
        model.addListener("Command", this);

        modelFactory.getChatModel().addListener("addMessage", this::messageAdded);
        modelFactory.getLoginModel().addListener("userAdded", this::userAdded);
        modelFactory.getLoginModel().addListener("userRemoved", this::userRemoved);
        loadMessages();
        loadUsers();
    }

    public void setHeaderProperty(String headerProperty) {
        this.headerProperty.set(headerProperty);
    }

    public StringProperty getInputProperty() {
        return inputProperty;
    }

    public StringProperty getHeaderProperty() {
        return headerProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public StringProperty getListProperty() {
        return listProperty;
    }


    private void messageAdded(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            messages.add((Message) event.getNewValue());
        });
    }

    public void sendMessage(String text) {
        Message message = new Message(text, modelFactory.getLoginModel().getUser().getUserName());
        modelFactory.getChatModel().sendMessage(message);
    }

    private void userAdded(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            userList.add((String) event.getNewValue());
        });
    }

    private void userRemoved(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            userList.remove((String) event.getNewValue());
        });
    }

    public void clear() {
        listProperty.set(null);
        inputProperty.set(null);
        errorProperty.set(null);
    }

    public void reset() {
        headerProperty.set(viewModelState.getUsername());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String newValue = listProperty.get() + headerProperty.get() + ": " + inputProperty.get() + "\n";
        listProperty.set(newValue);
    }

    public void logOut() {
        Platform.runLater(() -> {
            userList.remove(modelFactory.getLoginModel().getUser());
        });
    }


    //
    public ObservableList<Message> getMessages() {
        return messages;
    }

    //
    public ObservableList<String> getUserList() {

        return userList;
    }

    public void loadMessages() {
        List<Message> messageList = modelFactory.getChatModel().getMessages();
        messages = FXCollections.observableArrayList(messageList);
    }

    public void loadUsers() {
        List<String> users = modelFactory.getChatModel().getUsernames();
        userList = FXCollections.observableArrayList(users);
    }

}
