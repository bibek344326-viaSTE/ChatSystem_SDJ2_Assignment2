package mediator;


import model.Message;
import model.Subject;
import model.User;

import java.util.List;

public interface Client extends Subject {
    boolean isConnectionPossible(String username);

    void sendMessage(Message message);
    void startListeningToServer(User user);
    List<Message> getMessages();

    List<String> getUserList();

    boolean addUser(User user);

    boolean isLoginPossible(User user);

    void sendMessageToServer(String messageType, Object content);

//    void sendPrivateMessage(PrivateMessage privateMessage);
//
//    boolean doesPrivateMessageExists(String username1, String username2);
//
//    List<Message> getUsersMessage(PrivateMessage privateMessage);
}