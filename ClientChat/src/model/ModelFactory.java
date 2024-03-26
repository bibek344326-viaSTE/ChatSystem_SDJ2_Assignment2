package model;

import viewmodel.ClientFactory;

public class ModelFactory {
    private final ClientFactory cf;
    private ChatModel chatModel;
    private LoginModel loginModel;

    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }

    public ChatModel getChatModel() {
        if (chatModel == null) {
            this.chatModel = new ChatModelManager(cf.getClient());
        }
        return chatModel;
    }

    public LoginModel getLoginModel() {
        if (loginModel == null) {
            loginModel = new LoginModelManager(cf.getClient(),loginModel.getUser());
        }
        return loginModel;
    }
}

