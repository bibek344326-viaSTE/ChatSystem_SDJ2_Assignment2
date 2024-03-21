package mediator;

import model.Message;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private List<ServerHandler> connections = new ArrayList<>();

    public void addConnection(ServerHandler serverHandler) {
        connections.add(serverHandler);
    }

    public void broadcastToAll(Message message) {
        for (ServerHandler socketH : connections
        ) {
            socketH.sendMessageToClient(message);
        }
    }

    public void removeConnection(ServerHandler serverHandler) {
        connections.remove(serverHandler);
    }


//    public void broadCastPrivateMessage(PrivateChat privateMessage) {
//        for (ServerHandler socketH : connections
//        ) {
//            if (socketH.getUserName().equals(privateMessage.getUsername1()) || socketH.getUserName().equals(privateMessage.getUsername2())) {
//                socketH.sendPrivateMessageToClient(privateMessage.getSendMessage());
//            }
//        }
//    }

    public void broadCastUsername(String userName) {
        for (ServerHandler socketH : connections
        ) {
            socketH.sendUsersToClient(userName);

        }
    }

    public void broadcastUserDisconnected(String username) {
        for (ServerHandler socketH : connections
        ) {
            socketH.sendRemovedUserToClient(username);
        }
    }

    public int size() {
        return connections.size();
    }
}
