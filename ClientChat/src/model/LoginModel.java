package model;



public interface LoginModel  extends  Subject{
    boolean isConnectionPossible(String username);

    boolean addUser(String username, String password);

    boolean isLoginPossible(User user);


    User getUser();
}
