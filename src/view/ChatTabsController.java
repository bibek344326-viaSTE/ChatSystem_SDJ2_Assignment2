package view;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import utility.IntStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.ChatTabsViewModel;
public class ChatTabsController {
    @FXML
    private TextField globalMessage;
    @FXML
    private ListView globalList;
    @FXML
    private ListView onlineUsersList;
    @FXML
    private ListView messagesList;
    @FXML
    private TextField selected;
    @FXML
    private TextField sendMessage;
    @FXML
    private Label errorLabel;


private Region root;
private ViewHandler viewHandler;
private ChatTabsViewModel viewModel;

    public void init(ViewHandler viewHandler, VinylLendingViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        selected.textProperty().bind(viewModel.getSelected());
        viewModel.loadMessages();
        onlineUsersList.setItems(viewModel.getOnlineUsersList));
        messagesList.setItems(viewModel.getMessagesList());
        errorLabel.textProperty().bind(viewModel.getErrorLabel());
        messagesList.setItems(viewModel.getMessagesList());

        @FXML
        private void onSend() {
            viewModel.sendMessage(sendMessage.getText());
            sendMessage.clear();
        }

        @FXML
        private void onSelectUser(ActionEvent event) {
            viewModel.getSelectedItem().set(userList.getSelectionModel().getSelectedItem());
            viewModel.loadUsersMessage();
        }
        @FXML
        private void onLogOut()
        {
            viewModel.logOut();
            viewHandler.openLogin();
        }
        @FXML
        private void onSendPrivateMessage(ActionEvent event){
            viewModel.sendPrivate(sendPrivate.getText());

        }


    }
}