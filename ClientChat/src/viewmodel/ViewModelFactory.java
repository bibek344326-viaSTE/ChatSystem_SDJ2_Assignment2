package viewmodel;

import model.ChatModel;
import model.ModelFactory;
import model.User;

public class ViewModelFactory {

    private UserViewModel userViewModel;
    private ChatViewModel chatViewModel;
    private ViewModelState viewState;
    private ModelFactory modelFactory;

    public ViewModelFactory(ModelFactory modelFactory){
       this.modelFactory = modelFactory;
    }

    public UserViewModel getUserViewModel() {
        if (userViewModel == null) {
            userViewModel = new UserViewModel(modelFactory);
        }
        return userViewModel;
    }
    public ChatViewModel getChatViewModel() {
        if (chatViewModel == null) {
            chatViewModel = new ChatViewModel(modelFactory);
        }
        return chatViewModel;
    }
}
