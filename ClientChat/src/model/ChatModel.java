package model;

import java.util.List;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ChatModel extends Subject  {


    void sendMessage(Message message);

    void setUsername(User username);

    List<Message> getMessages();

    List<String> getUsernames();
}
