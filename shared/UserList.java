import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public ArrayList<String> allUserNames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            usernames.add(users.get(i).getUserName());
        }
        return usernames;
    }

    public boolean contains(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (user.equals(users.get(i))){
                return true;
            }
        }
        return false;
    }
    public int size()
    {
        return users.size();
    }
    public User get(int index){
        return users.get(index);
    }
}
