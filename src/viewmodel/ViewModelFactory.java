package viewmodel;

import model.Model;



public class ViewModelFactory {
    private final ViewState viewState;
    private final ChatTabsViewModel chatTabsViewModel;
    private final LoginViewModel loginViewModel;

    private final SignUpViewModel signUpViewModel;

    public ViewModelFactory(Model model) {
        this.viewState = new ViewState();
        this.chatTabsViewModel = new ChatTabsViewModel(model, viewState);
        this.loginViewModel = new LoginViewModel(model, viewState);
        this.signUpViewModel = new SignUpViewModel(model, viewState);
    }


    public ChatTabsViewModel getChatTabsViewModel() {
        return chatTabsViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public SignUpViewModel getSignUpViewModel() {
        return signUpViewModel;
    }
}