package model;

import mediator.Client;

public class LoginModelManager implements LoginModel {
    private Client client;
    private User user;

    public LoginModelManager(Client client, User user) {
        this.client = client;
        this.user = user;
    }


    @Override
    public boolean isConnectionPossible(String username) {
        return client.isConnectionPossible(username);
    }


    @Override
    public boolean addUser(String username, String password) {
        User tempUser = new User(username, password);
        return client.addUser(tempUser);

    }

    @Override
    public boolean isLoginPossible(User user) {
        boolean loginPossible = client.isLoginPossible(user);
        if (loginPossible) this.user = user;
        return loginPossible;

    }

    @Override
    public User getUser() {
        return user;
    }


}
