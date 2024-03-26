package utils;

import java.io.Serializable;

public interface MessageFactory {
    Serializable createMessage(String messageType, Object arg);
}