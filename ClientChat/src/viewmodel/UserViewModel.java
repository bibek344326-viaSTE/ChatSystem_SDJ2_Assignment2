package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;
import model.User;

public class UserViewModel
{
    private StringProperty errorProperty;
    private StringProperty usernameProperty;
    private ChatModel model;
    private ViewModelState viewState;



    public UserViewModel(ChatModel model, ViewModelState viewState)
    {
        this.model = model;
        this.viewState = viewState;

        errorProperty = new SimpleStringProperty();
        usernameProperty = new SimpleStringProperty();
    }

    public void reset()
    {
        errorProperty.set(null);
        User userName = model.getUsername();
        usernameProperty.set(userName.getUsername());
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

}

