package mediator;

import model.*;
import utils.MessageFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;

public class ServerHandler implements Runnable {

    private Socket socket;
    private ChatModelManager chatModelManager;
    private ObjectOutputStream outToClient;
    private ObjectInputStream inFromClient;
    private User user;
    private ConnectionPool pool;
    private LoginModelManager loginModelManager;
    private MessageFactory messageFactory;


    public ServerHandler(Socket socket, ChatModelManager chatModelManager, LoginModelManager loginModelManager, ConnectionPool pool, MessageFactory messageFactory) {
        this.socket = socket;
        this.pool = pool;
        this.chatModelManager = chatModelManager;
        this.loginModelManager = loginModelManager;
        this.messageFactory = messageFactory;

        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {                                                                   // try should be outside the while(true) to not print the stacktrace forever.
            while (true) {
                Request request = (Request) inFromClient.readObject();
                if ("connectionRequest".equals(request.getType())) {                // switch case would have been better.......
                    String temp = (String) request.getArg();
                    if (loginModelManager.isConnectionPossible(temp)) {
                        outToClient.writeObject(new Request("connectionRequest", true));
//                        pool.broadCastUsername(temp);
                    } else {
                        outToClient.writeObject(new Request("connectionRequest", false));
                    }
                    break;
                    //socket.close();

                } else if ("Listener".equals(request.getType())) {
                    this.user = (User) request.getArg();
                    // todo conn pool stuff
                    pool.addConnection(this);

                    //  chatHandler.addListener("addMessage", this::messageAdded);
//                    model.addListener("userAdded", this::userAdded);
//                    model.addListener("userRemoved", this::userRemoved);
                } else if ("getMessage".equals(request.getType())) {
                    List<Message> temp = chatModelManager.getMessages();
                    outToClient.writeObject(new Request("getMessage", temp));
                    break;
                } else if ("addMessage".equals(request.getType())) {
                    Message message = (Message) request.getArg();
                    String messageType = "standard";
                    Message newMessage = (Message) messageFactory.createMessage(messageType, message);

                    chatModelManager.addMessage(newMessage);
                    pool.broadcastToAll(newMessage);
                    break;
                }
//
//
                else if ("addUser".equals(request.getType())) {

                    boolean temp = loginModelManager.addUser((User) request.getArg());
                    outToClient.writeObject(new Request("addUser", temp));
                    break;

                } else if ("isLoginPossible".equals(request.getType())) {
                    User user = (User) request.getArg();
                    boolean temp = loginModelManager.isLoginPossible(user);
                    outToClient.writeObject(new Request("isLoginPossible", temp));
                    if (temp) {
                        // setUser(user);
                        this.user = user;
                        pool.broadCastUsername(user.getUserName());
                    }
                    break;

                } else if ("getUserList".equals(request.getType())) {
                    outToClient.writeObject(new Request("getUserList", loginModelManager.getAllUsers()));
                    break;
//                } else if ("addPrivateMessage".equals(request.getType())) {
//                    //  chatHandler.addPrivateMessage((Object[]) request.getArg());
//                    chatModelManager.addPrivateMessage((PrivateChat) request.getArg());
//                    //  outToClient.writeObject(new Request(null,null));
//                    pool.broadCastPrivateMessage((PrivateChat) request.getArg());
//                    outToClient.writeObject(new Request(null, null));
//                    break;
//                    //  pool.broadcast()
//                } else if ("getUsersMessage".equals(request.getType())) {
//                    List<Message> privateMessages = chatModelManager.getPrivateMessage((PrivateChat) request.getArg());
//                    outToClient.writeObject(new Request("getUsersMessage", privateMessages));
//                    break;

                }
//                else if ("addPrivateMessage".equals(request.getType())){
//                    String username1 =((String[]) request.getArg())[0];
//                    String username2= ((String[]) request.getArg())[1];
//                    outToClient.writeObject(new Request("doesPrivateMessageExists",chatHandler.doesPrivateMessageExists(username1,username2)));
//                }

            }

            socket.close();
        } catch (IOException e) {
            //loginHandler.removeUser(this.user);
            System.out.println("Socket has been disconnected");
            pool.removeConnection(this);
            pool.broadcastUserDisconnected(getUserName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

//    private void messageAdded(PropertyChangeEvent event) {
//        Message temp = (Message) event.getNewValue();
//        try {
//            //  System.out.println(userName);
//            outToClient.writeObject(new Request("addMessage", temp));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void sendMessageToClient(Message message) {

        try {
            outToClient.writeObject(new Request("addMessage", message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        if (user == null) {
            return "";
        } else {
            return user.getUserName();
        }
    }

    public void sendUsersToClient(String userName) {
        try {
            outToClient.writeObject(new Request("userAdded", userName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRemovedUserToClient(String username) {
        try {
            outToClient.writeObject(new Request("userRemoved", username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPrivateMessageToClient(Message message) {
        {
            try {
                outToClient.writeObject(new Request("addPrivateMessage", message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
