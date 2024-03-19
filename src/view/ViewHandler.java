package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {
    ViewModelFactory factory;
    private Stage primaryStage;
    private Scene currentScene;
    //  Controllers
    private ChatTabsController chatTabsController;
    private LoginController loginController;
    private SignUpController signUpController;

    public ViewHandler(ViewModelFactory factory) {
        this.factory = factory;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.currentScene = new Scene(new Region());
        openView("chat");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "chat":
                root = loadChatView("ChatTabs.fxml");
                break;
            case "login":
                root = loadLoginView("Login.fxml");
                break;
            case "signup":
                root = loadSignUpView("SignUp.fxml");
                break;
        }
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.show();

    }

    public Region loadChatView(String fxmlFile) {
        Region root = null;
        if (chatTabsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                chatTabsController = loader.getController();
                chatTabsController.init(this, factory.getChatTabsViewModel(), root);
            } catch (Exception e) {
                System.out.println("Could not load view for fxml file: " + fxmlFile);
                System.out.println(e.getMessage());
            }
        } else {
            chatTabsController.reset();
        }
        return chatTabsController.getRoot();

    }

    public Region loadLoginView(String fxmlFile) {

        if (loginController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                loginController = loader.getController();
                loginController.init(this, factory.getLoginViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
           loginController.reset();
        }
        return loginController.getRoot();
    }
    public Region loadSignUpView(String fxmlFile) {

        if (signUpController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                signUpController = loader.getController();
                signUpController.init(this, factory.getSignUpViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            signUpController.reset();
        }
        return signUpController.getRoot();
    }
}
