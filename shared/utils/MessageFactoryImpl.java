import utils.MessageFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MessageFactoryImpl implements MessageFactory {

    private final Map<String, Class<?>> messageTypes;

    public MessageFactoryImpl() {
        // Initialize the mapping between message types and their corresponding classes
        messageTypes = new HashMap<>();
        messageTypes.put("Request", Request.class);
        messageTypes.put("Message", Message.class);
        messageTypes.put("PrivateMessage", PrivateMessage.class);
        messageTypes.put("User", User.class);
    }

    @Override
    public Serializable createMessage(String messageType, Object content) {
        try {
            Class<?> messageClass = messageTypes.get(messageType);
            if (messageClass != null) {
                return (Serializable) messageClass.getDeclaredConstructor(Object.class).newInstance(content);
            } else {
                throw new IllegalArgumentException("Unknown message type: " + messageType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}