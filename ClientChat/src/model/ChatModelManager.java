package model;

import java.util.List;

public class ChatModelManager implements ChatModel {
    private Client client;


    public ChatModelManager(Client client) {
        this.client = client;

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
    public void sendPrivateMessage(PrivateChat privateMessage) {
        client.sendPrivateMessage(privateMessage);
    }

    @Override
    public boolean doesPrivateMessageExists(String username1, String username2) {
        return client.doesPrivateMessageExists(username1, username2);
    }

    @Override
    public List<Message> getUsersMessage(String userName1, String userName2) {
        PrivateChat privateMessage = new PrivateChat(userName1, userName2);
        return client.getUsersMessage(privateMessage);
    }
}

