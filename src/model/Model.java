package model;

import java.util.List;
import utility.observer.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    boolean addUser(User user);
    void removeUser(User user);
    boolean isConnectionPossible(String userName);

    boolean isLoginPossible(User arg);

    List<String> getAllUsers();

    List<Message> getMessages();
    void addMessage(Message message);

    void addPrivateMessage(PrivateChat privateMessage);

    List<Message> getPrivateMessage(PrivateChat arg);
    // boolean doesPrivateMessageExists(String username1, String username2);

}
