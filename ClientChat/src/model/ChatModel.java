package model;

import java.util.List;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ChatModel extends NamedPropertyChangeSubject  {


    void sendMessage(Message message);
    List<Message> getMessages();

    List<String> getUsernames();

    User getUsername();

    void setUsername(User username);


//    void sendPrivateMessage(PrivateChat privateChat);
//    boolean doesPrivateMessageExists(String username1, String username2);
//
//    List<Message> getUsersMessage(String userName1, String username2);
}
