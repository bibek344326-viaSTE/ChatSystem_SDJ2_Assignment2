package viewmodel;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Message;
import model.PrivateChat;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ChatTabsViewModel implements PropertyChangeListener {
    private Model model;

    private ViewState viewstate;
    private ObservableList<Message> messages;
    private ObservableList<String> userList;
    private ObservableList<Message> privateMessages;
    private StringProperty selectedItem, userError;



    public ChatTabsViewModel(Model model) {
        this.model = model;
        this.model.addListener(this);
        selectedItem = new SimpleStringProperty();
        userError = new SimpleStringProperty();
        privateMessages = FXCollections.observableArrayList();
        loadMessages();
        loadUsers();

        //  message= new SimpleStringProperty();
    }

    private void privateMessageAdded(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            privateMessages.add((Message) event.getNewValue());
        });
    }

    private void messageAdded(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            messages.add((Message) event.getNewValue());
        });
    }

    public StringProperty getUserError() {
        return userError;
    }

    //
//    }
//
    void loadMessages() {
        List<Message> messageList = model.getMessages();
        messages = FXCollections.observableArrayList(messageList);
    }

    void loadUsers() {
        List<String> users = model.getUsernames();
        userList = FXCollections.observableArrayList(users);
    }
    void loadUsersMessage() {

        List<Message> usersMessage= model.getUsersMessage(model.getUser().getUserName(),selectedItem.get());
        //   privateMessages= FXCollections.observableArrayList(usersMessage);
        privateMessages.setAll(usersMessage);

    }

    public void sendMessage(String text) {
        Message message = new Message(text, model.getUser().getUserName());
        model.addMessage(message);
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

    public StringProperty getSelectedItem() {
        return selectedItem;
    }

    //
    public ObservableList<Message> getMessages() {
        return messages;
    }

    //
    public ObservableList<String> getUserList() {

        return userList;
    }
    //  public String


    public void logOut() {
        Platform.runLater(() -> {
            userList.remove(modelFactory.getLoginModel().getUser());
        });
    }

    public void sendPrivate(String text) {
        String username1 = modelFactory.getLoginModel().getUser().getUserName();
        String username2 = selectedItem.get();
        if (username2.equals("") || username2 == null) {
            userError.set("Select a user to send message first");
        } else {
            Message message = new Message(text, username1);
            PrivateMessage sendMessage = new PrivateMessage(username1, username2, message);
            modelFactory.getChatModel().sendPrivateMessage(sendMessage);
            userError.set("");
        }
    }

    public ObservableList<Message> getPrivateMessages() {
        return privateMessages;
    }

}