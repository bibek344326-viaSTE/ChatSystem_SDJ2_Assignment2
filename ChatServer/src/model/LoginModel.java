package model;

import java.util.List;

public interface LoginModel {
    boolean addUser(User user);
    void removeUser(User user);
    boolean isConnectionPossible(String userName);

    boolean isLoginPossible(User arg);

    List<String> getAllUsers();

}
