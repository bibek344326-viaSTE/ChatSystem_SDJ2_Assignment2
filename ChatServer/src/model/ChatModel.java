package model;

import java.util.List;

public interface ChatModel {
    List<Message> getMessages();
    void addMessage(Message message);

    void addPrivateMessage(PrivateChat privateMessage);

    List<Message> getPrivateMessage(PrivateChat arg);
    // boolean doesPrivateMessageExists(String username1, String username2);
}
