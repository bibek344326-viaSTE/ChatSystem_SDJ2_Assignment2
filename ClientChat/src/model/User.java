package model;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;

    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
    }
    public User(String userName)
    {
        this.userName=userName;
        this.password=null;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {

        return password;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return userName.equals(other.userName) &&
                password.equals(other.password);

    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
