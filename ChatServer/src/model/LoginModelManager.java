package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class LoginModelManager {
    private UserList everyUsers;

    public LoginModelManager()
    {
        everyUsers= new UserList();
    }
    public boolean addUser(User user) {
        everyUsers.addUser(user);
//        for (int i = 0; i < everyUsers.size(); i++) {
//            System.out.println(everyUsers.get(i));
//        }
//        System.out.println(everyUsers);
        return true;
    }

    public void removeUser(User user) {
        everyUsers.removeUser(user);
    }

    public boolean isConnectionPossible(String userName) {
        return !(everyUsers.allUserNames().contains(userName));
    }

    public boolean isLoginPossible(User user) {
        return everyUsers.contains(user);
    }

    public List<String> getAllUsers() {
        return everyUsers.allUserNames();
    }

}
