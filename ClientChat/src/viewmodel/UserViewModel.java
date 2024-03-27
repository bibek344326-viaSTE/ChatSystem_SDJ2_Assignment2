package viewmodel;

import com.sun.webkit.Timer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;
import model.ModelFactory;
import model.User;

public class UserViewModel
{
    private StringProperty errorProperty;
    private StringProperty usernameProperty;
    private ChatModel model;
    private ViewModelState viewState;
    private ModelFactory modelFactory;



    public UserViewModel(ModelFactory modelFactory)
    {
      this.modelFactory = modelFactory;

        errorProperty = new SimpleStringProperty();
        usernameProperty = new SimpleStringProperty();
    }

    public void reset()
    {
        errorProperty.set(null);
    }

    public StringProperty getErrorProperty()
    {
        return errorProperty;
    }

    public StringProperty getUsernameProperty()
    {
        return usernameProperty;
    }


    public void setUsername(User username) {
        this.model.setUsername(username);
    }
    public boolean onLogin(String username) {
        User user = new User(username);
        //System.out.println(user);
        if(!modelFactory.getLoginModel().isLoginPossible(user)) {
            errorProperty.set("Username not available");
            return false;
        }
        return true;
    }

}

