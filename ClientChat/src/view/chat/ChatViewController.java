package view.chat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewController;
import viewmodel.ChatViewModel;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import static javafx.beans.binding.Bindings.bindBidirectional;

public class ChatViewController implements ViewController
{
    public ListView listView;
    public TextField messageTextField;
    //@FXML private Label headerLabel;
    @FXML private TextArea viewList;
    @FXML private TextField inputField;
    @FXML private Label errorLabel;
    private Region root;
    private ViewHandler viewHandler;
    private ChatViewModel chatViewModel;

    //initializations and bindings

    public void reset()
    {
        chatViewModel.reset();
    }




    //triggered by pressing the Send button
    @FXML public void sendPressed()
    {
        chatViewModel.sendMessage(inputField.getText());
        inputField.clear();
    }

    @FXML public void logOffPressed()
    {
        chatViewModel.logOut();
        viewHandler.openLogin();

    }

    @Override
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.chatViewModel = viewModelFactory.getChatViewModel();
        chatViewModel.loadMessages();
      //  headerLabel.textProperty().bindBidirectional(chatViewModel.getHeaderProperty());
        listView.setItems(chatViewModel.getMessages());
       inputField.textProperty().bindBidirectional(chatViewModel.getInputProperty());
        errorLabel.textProperty().bind(chatViewModel.getErrorProperty());

        reset();
    }
}