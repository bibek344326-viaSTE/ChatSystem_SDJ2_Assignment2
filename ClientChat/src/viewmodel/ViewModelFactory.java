package viewmodel;

import model.ChatModel;
import model.User;

public class ViewModelFactory {

    private UserViewModel userViewModel;
    private ChatViewModel chatViewModel;
    private ViewModelState viewState;

    public ViewModelFactory(ChatModel model){
        viewState = new ViewModelState();
        userViewModel = new UserViewModel(model, viewState);
        chatViewModel = new ChatViewModel(model, viewState);
    }
    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public ChatViewModel getChatViewModel() {
        return chatViewModel;
    }
}
