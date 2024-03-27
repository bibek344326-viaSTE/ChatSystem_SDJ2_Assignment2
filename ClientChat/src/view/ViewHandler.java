package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import view.chat.ChatViewController;
import view.user.UserViewController;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler
{
    private Stage stage;
    private Scene chatScene, userLoginScene ;
    private ViewModelFactory viewModelFactory;



    public ViewHandler(ViewModelFactory viewModelFactory)
    {
        this.viewModelFactory = viewModelFactory;
       // currentScene = new Scene(new Region());
    }

    public void start()
    {
        this.stage = new Stage();
        openLogin();
    }

    public void openChat() {
        if (chatScene == null) {
            try {
                Parent root = loadFXML("../view/chat/Chat.fxml");
                stage.setTitle("Chat");
                chatScene = new Scene(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(chatScene);
        stage.show();

    }
    public void openLogin() {
        if (userLoginScene == null) {
            try {
                Parent root = loadFXML("../view/user/User.fxml");
                userLoginScene = new Scene(root);
                stage.setTitle("User");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(userLoginScene);
        stage.show();
    }

    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, viewModelFactory);
        return root;
    }

}
