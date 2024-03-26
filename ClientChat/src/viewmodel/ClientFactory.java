package viewmodel;

import mediator.Client;
import mediator.ClientSocket;
import utils.MessageFactory;

public class ClientFactory {
    private Client client;
    private final MessageFactory messageFactory;

    public ClientFactory(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    public Client getClient(MessageFactory messageFactory){
        if(client==null){
            client = new ClientSocket(messageFactory);
        }
        return client;
    }
}