package mediator;

import model.ChatModelManager;
import model.LoginModelManager;
import utils.MessageFactory;
import utils.MessageFactoryImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private ChatModelManager chatModelManager;
    private LoginModelManager loginModelManager;


    public SocketServer(ChatModelManager chatModelManager, LoginModelManager loginModelManager) {
        this.chatModelManager = chatModelManager;
        this.loginModelManager = loginModelManager;

    }

    public void startServer() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(9009);
            System.out.println("Server started....");
            ConnectionPool cp = new ConnectionPool();

            MessageFactory messageFactory = new MessageFactoryImpl();

            while (true) {
                System.out.println("Waiting for clients.....");
                Socket socket = welcomeSocket.accept();
                //  System.out.println(socket.getInetAddress().getHostAddress() + "  identified");
                ServerHandler serverHandler = new ServerHandler(socket, chatModelManager, loginModelManager, cp,messageFactory);
                // System.out.println("A new server handler is created");
                // cp.addConnection(serverHandler);
                System.out.println(cp.size());
                Thread t = new Thread(serverHandler);
                Logger logger = Logger.getInstance();
                logger.log(serverHandler.getUserName(), socket.getInetAddress().getHostAddress());
                t.start();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
