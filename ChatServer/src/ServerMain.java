import mediator.SocketServer;
import model.ChatModelManager;
import model.LoginModelManager;

public class ServerMain {
    public static void main(String[] args) {
        {
            SocketServer socketServer = new SocketServer(new ChatModelManager(), new LoginModelManager());
            socketServer.startServer();
        }
    }
}

